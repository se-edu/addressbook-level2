package seedu.addressbook;

import static seedu.addressbook.TextUi.*;
import static seedu.addressbook.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.Parser.Command;
import seedu.addressbook.model.InvalidDataException;
import seedu.addressbook.model.Tag;
import seedu.addressbook.model.UniqueTagList;
import seedu.addressbook.model.person.*;

import java.io.*;
import java.util.*;

/* ==============NOTE TO STUDENTS======================================
 * This class header comment below is brief because details of how to
 * use this class are documented elsewhere.
 * ====================================================================
 */

/**
 * Entry point of the address book application.
 * Contains command execution logic; initialises and uses the different components.
 */
public class Main implements Runnable {

    /**
     * Default file path used if the user doesn't provide the file name.
     */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";

    /**
     * These message is not in the TextUi class because program launch and initialisation messages may be shown
     * outside the given UI (should be logged at launch console), and needs to be shown before the UI is ready.
     */
    public static final String MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE = "Launch arguments format: [STORAGE_FILE_PATH]";
    public static final String MESSAGE_INIT_FAILED = "Failed to initialise address book application. Exiting...";

    /**
     * Version info of the program.
     */
    public static final String VERSION = "AddessBook Level 1 - Version 1.0";

    /**
     * Signals that the main application had a problem while initialising.
     */
    public static class MainInitialisationException extends Exception {}

    private final Parser parser;
    private final TextUi ui;
    private final StorageFile storageFile;
    private final AddressBook addressBook;

    /**
     * Sets up the different components and loads up the model from the storage file.
     *
     * @param storageFilePath file path of the desired storage file
     * @param inputStream user text input source
     * @param outputStream user text output acceptor
     *
     * @throws MainInitialisationException if there were problems
     */
    public Main(String storageFilePath, InputStream inputStream, PrintStream outputStream)
            throws MainInitialisationException {
        this.parser = new Parser();
        this.ui = new TextUi(inputStream, outputStream, parser);

        try {
            this.storageFile = new StorageFile(storageFilePath);
            this.addressBook = storageFile.loadAddressBookFromFile();

        } catch (InvalidStorageFilePathException isfpe) {
            ui.showToUser(MESSAGE_INVALID_STORAGE_FILE_PATH);
            throw new MainInitialisationException();
        } catch (FileNotFoundException fnfe) {
            ui.showToUser(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, storageFilePath));
            throw new MainInitialisationException();
        } catch (IOException ioe) {
            ui.showToUser(String.format(MESSAGE_ERROR_READING_FROM_FILE, storageFilePath));
            throw new MainInitialisationException();
        }
    }

    /**
     * Entry point.
     */
    public static void main(String... launchArgs) {
        System.out.println(MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE);
        try {
            final Main main = new Main(getStorageFilePathFromLaunchArgs(launchArgs), System.in, System.out);
            main.run();
        } catch (MainInitialisationException mie) {
            System.out.println(MESSAGE_INIT_FAILED);
        }
    }

    /**
     * Retrieves the string representing the intended storage file path as specified in the launch args.
     * Defaults to {@link #DEFAULT_STORAGE_FILEPATH} if no storage file argument is found.
     * 
     * @param launchArgs full program launch arguments passed to application main method
     */
    private static String getStorageFilePathFromLaunchArgs(String... launchArgs) {
        return launchArgs.length > 0 ? launchArgs[0] : DEFAULT_STORAGE_FILEPATH;
    }

    /**
     * Displays the goodbye message and exits the runtime.
     */
    private void exitProgram() {
        ui.showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
        System.exit(0);
    }

    /*
     * ==============NOTE TO STUDENTS======================================
     * Notice how this method solves the whole problem at a very high level.
     * We can understand the high-level logic of the program by reading this
     * method alone.
     * ====================================================================
     */

    /**
     * Starts program execution after all dependencies and components successfully initialised.
     */
    @Override
    public void run() {
        ui.showWelcomeMessage(VERSION);
        ui.showToUser(String.format(MESSAGE_USING_STORAGE_FILE, storageFile.toString()));
        // [read input, execute, response] loop
        while (true) {
            String userCommand = ui.getUserCommand();
            ui.echoLastEnteredUserCommand();
            String feedback = executeCommand(userCommand);
            ui.showResultToUser(feedback);
        }
    }

    /*
     * ===========================================
     *           COMMAND LOGIC
     * ===========================================
     */

    /**
     * Checks which command the user want to trigger, then run the corresponding function
     * 
     * @param userInputString  raw input from user
     * @return  feedback about how the command was executed
     */
    private String executeCommand(String userInputString) {
        try {
            final Command commandType = parser.identifyCommand(userInputString);
            final String commandArgs = parser.extractArguments(userInputString);
            switch (commandType) {
            case ADD:
                return executeAddPerson(commandArgs);
            case DELETE:
                return executeDeletePerson(commandArgs);
            case CLEAR:
                return executeClearAddressBook();
            case FIND:
                return executeFindPersons(commandArgs);
            case LIST:
                return executeListAllPersonsInAddressBook();
            case VIEW:
                return executeViewPerson(commandArgs);
            case VIEW_ALL:
                return executeViewAllDetailsOfPerson(commandArgs);
            case HELP:
                return getUsageInfoForAllCommands();
            case EXIT: // fallthrough
                executeExitProgramRequest();
            default:
                throw new IllegalStateException(); // should never reach this line.
            }
        } catch (Parser.InvalidCommandFormatException icfe) {
            return getMessageForInvalidCommandInput(userInputString, getUsageInfoForAllCommands());
        }
    }

    /**
     * Adds a person (specified by the command args) to the address book.
     * The entire command arguments string is treated as a string representation of the person to add.
     *
     * @param addArgs full command args string from the user
     * @return feedback display message for the operation result
     * @see #getPersonFromAddCommandArgs(String)
     */
    private String executeAddPerson(String addArgs) {
        try {
            final Person personToAdd = getPersonFromAddCommandArgs(addArgs);
            addressBook.addPerson(personToAdd);
            saveChangesToStorageFile();
            return String.format(MESSAGE_ADD_PERSON_SUCCESS, personToAdd);

        } catch (Parser.InvalidCommandFormatException icfe) {
            return getMessageForInvalidCommandInput(ui.getLastEnteredCommand(), getUsageInfoForAddCommand());
        } catch (InvalidDataException ide) {
            return ide.getMessage();
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return MESSAGE_DUPLICATE_PERSON;
        }
    }

    /**
     * Saves any changes to the storage file. Exits program if an error is encountered.
     */
    private void saveChangesToStorageFile() {
        try {
            storageFile.saveAddressBookToFile(addressBook);
            ui.showToUser(MESSAGE_CHANGES_SAVED_TO_STORAGE_FILE);
        } catch (FileNotFoundException fnfe) {
            ui.showToUser(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, storageFile));
            exitProgram();
        } catch (IOException ioe) {
            ui.showToUser(String.format(MESSAGE_ERROR_WRITING_TO_FILE, storageFile));
            exitProgram();
        }
    }

    /**
     * Extracts the specified person to add from the add command's arguments.
     *
     * @param addArgs full add command args string form user
     * @return successfully constructed person from the arguments
     * @throws Parser.InvalidCommandFormatException if the arguments format is invalid
     * @throws InvalidDataException if any person data field constraint is not fulfilled
     */
    private Person getPersonFromAddCommandArgs(String addArgs) throws
            Parser.InvalidCommandFormatException, InvalidDataException {
        final Name name = new Name(parser.extractNameFromAddCommandArgs(addArgs));
        final Phone phone = new Phone(parser.extractPhoneFromAddCommandArgs(addArgs),
                parser.isPhonePrivateFromAddCommandArgs(addArgs));
        final Email email = new Email(parser.extractEmailFromAddCommandArgs(addArgs),
                parser.isEmailPrivateFromAddCommandArgs(addArgs));
        final Address address = new Address(parser.extractAddressFromAddCommandArgs(addArgs),
                parser.isAddressPrivateFromAddCommandArgs(addArgs));

        final List<String> tagStrings = parser.extractTagsFromAddCommandArgs(addArgs);
        // merge duplicate tags
        final Set<Tag> tags = new HashSet<>();
        for (String tagString : tagStrings) {
            tags.add(new Tag(tagString));
        }
        // tag list prepared
        final UniqueTagList tagList = new UniqueTagList(tags);

        return new Person(name, phone, email, address, tagList);
    }

    /**
     * Deletes person identified using last displayed index.
     *
     * @param deleteArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private String executeDeletePerson(String deleteArgs) {
        try {
            final int targetDisplayedIndex = parser.extractIndexFromDeleteCommandArgs(deleteArgs);
            final ReadOnlyPerson target = ui.getPersonFromLastShownListing(targetDisplayedIndex);
            addressBook.removePerson(target);
            saveChangesToStorageFile();
            return String.format(MESSAGE_DELETE_PERSON_SUCCESS, target);

        } catch (Parser.InvalidCommandFormatException icfe) {
            return getMessageForInvalidCommandInput(ui.getLastEnteredCommand(), getUsageInfoForDeleteCommand());
        } catch (IndexOutOfBoundsException ioobe) {
            return MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;
        }
    }

    /**
     * Clears all persons in the address book.
     *
     * @return feedback display message for the operation result
     */
    private String executeClearAddressBook() {
        addressBook.clear();
        saveChangesToStorageFile();
        return MESSAGE_ADDRESSBOOK_CLEARED;
    }

    /**
     * Finds and lists all persons in address book whose name contains any of the argument keywords.
     * Keyword matching is case sensitive.
     * 
     * @param commandArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private String executeFindPersons(String commandArgs) {
        try {
            final Set<String> keywords = parser.extractKeywordsFromFindCommandArgs(commandArgs);
            final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
            ui.showPersonListView(personsFound);
            return getMessageForPersonListShownSummary(personsFound);
        } catch (Parser.InvalidCommandFormatException icfe) {
            return getMessageForInvalidCommandInput(ui.getLastEnteredCommand(), getUsageInfoForDeleteCommand());
        }
    }

    /**
     * Retrieve all persons in the full model whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons in full model with name containing some of the keywords
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Collection<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersonsImmutableView()) {
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    private static String getMessageForPersonListShownSummary(List<? extends ReadOnlyPerson> personsDisplayed) {
        return String.format(MESSAGE_PERSONS_FOUND_OVERVIEW, personsDisplayed.size());
    }

    /**
     * Displays all persons in the address book to the user; in added order.
     *
     * @return feedback display message for the operation result
     */
    private String executeListAllPersonsInAddressBook() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersonsImmutableView();
        ui.showPersonListView(allPersons);
        return getMessageForPersonListShownSummary(allPersons);
    }

    /**
     * Shows details of the person identified using the last displayed index.
     * Private contact details are not shown.
     *
     * @param viewArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private String executeViewPerson(String viewArgs) {
        try {
            final int targetDisplayedIndex = parser.extractIndexFromViewCommandArgs(viewArgs);
            final ReadOnlyPerson target = ui.getPersonFromLastShownListing(targetDisplayedIndex);
            if (!addressBook.containsPerson(target)) {
                return MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;
            }
            return String.format(MESSAGE_VIEW_PERSON_DETAILS, target.getAsTextHidePrivate());
        } catch (Parser.InvalidCommandFormatException icfe) {
            return getMessageForInvalidCommandInput(ui.getLastEnteredCommand(), getUsageInfoForViewCommand());
        } catch (IndexOutOfBoundsException ioobe) {
            return MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        }
    }

    /**
     * Shows all details of the person identified using the last displayed index.
     *
     * @param viewAllArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private String executeViewAllDetailsOfPerson(String viewAllArgs) {
        try {
            final int targetDisplayedIndex = parser.extractIndexFromViewAllCommandArgs(viewAllArgs);
            final ReadOnlyPerson target = ui.getPersonFromLastShownListing(targetDisplayedIndex);
            if (!addressBook.containsPerson(target)) {
                return MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;
            }
            return String.format(MESSAGE_VIEW_PERSON_DETAILS, target.getAsTextShowAll());
        } catch (Parser.InvalidCommandFormatException icfe) {
            return getMessageForInvalidCommandInput(ui.getLastEnteredCommand(), getUsageInfoForViewAllCommand());
        } catch (IndexOutOfBoundsException ioobe) {
            return MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        }
    }

    /**
     * Request to terminate the program.
     *
     * @return feedback display message for the operation result
     */
    private void executeExitProgramRequest() {
        exitProgram();
    }

}