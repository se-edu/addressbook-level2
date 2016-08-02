package seedu.addressbook;
/* ==============NOTE TO STUDENTS======================================
 * This class is written in a procedural fashion (i.e. not Object-Oriented)
 * Yes, it is possible to write non-OO code using an OO language.
 * ====================================================================
 */

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/* ==============NOTE TO STUDENTS======================================
 * This class header comment below is brief because details of how to
 * use this class are documented elsewhere.
 * ====================================================================
 */

/**
 * This class is used to maintain a list of person data which are saved
 * in a text file.
 **/
public class AddressBook {

    /**
     * Default file path used if the user doesn't provide the file name.
     */
    private static final String DEFAULT_STORAGE_FILEPATH = "addressbook.txt";

    /**
     * Version info of the program.
     */
    private static final String VERSION = "AddessBook Level 1 - Version 1.0";

    /**
     * A decorative prefix added to the beginning of lines printed by AddressBook
     */
    public static final String LINE_PREFIX = "|| ";

    /**
     * A platform independent line separator.
     */
    private static final String LS = System.lineSeparator() + LINE_PREFIX;

    /*
     * ==============NOTE TO STUDENTS======================================
     * These messages shown to the user are defined in one place for convenient
     * editing and proof reading. Such messages are considered part of the UI
     * and may be subjected to review by UI experts or technical writers. Note
     * that Some of the strings below include '%1$s' etc to mark the locations
     * at which java String.format(...) method can insert values.
     * ====================================================================
     */
    private static final String MESSAGE_ADDED = "New person added: %1$s, Phone: %2$s, Email: %3$s";
    private static final String MESSAGE_ADDRESSBOOK_CLEARED = "Address book has been cleared!";
    private static final String MESSAGE_COMMAND_HELP = "%1$s: %2$s";
    private static final String MESSAGE_COMMAND_HELP_PARAMETERS = "\tParameters: %1$s";
    private static final String MESSAGE_COMMAND_HELP_EXAMPLE = "\tExample: %1$s";
    private static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    private static final String MESSAGE_DISPLAY_PERSON_DATA = "%1$s  Phone Number: %2$s  Email: %3$s";
    private static final String MESSAGE_DISPLAY_LIST_ELEMENT_INDEX = "%1$d. ";
    private static final String MESSAGE_GOODBYE = "Exiting Address Book... Good bye!";
    private static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format: %1$s " + LS + "%2$s";
    private static final String MESSAGE_INVALID_FILE = "The given file name [%1$s] is not a valid file name!";
    private static final String MESSAGE_INVALID_PROGRAM_ARGS = "Too many parameters! Correct program argument format:"
                                                            + LS + "\tjava AddressBook"
                                                            + LS + "\tjava AddressBook [custom storage file path]";
    private static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    private static final String MESSAGE_INVALID_STORAGE_FILE_CONTENT = "Storage file has invalid content";
    private static final String MESSAGE_PERSON_NOT_IN_ADDRESSBOOK = "Person could not be found in address book";
    private static final String MESSAGE_ERROR_CREATING_STORAGE_FILE = "Error: unable to create file: %1$s";
    private static final String MESSAGE_ERROR_MISSING_STORAGE_FILE = "Storage file missing: %1$s";
    private static final String MESSAGE_ERROR_READING_FROM_FILE = "Unexpected error: unable to read from file: %1$s";
    private static final String MESSAGE_ERROR_WRITING_TO_FILE = "Unexpected error: unable to write to file: %1$s";
    private static final String MESSAGE_PERSONS_FOUND_OVERVIEW = "%1$d persons found!";
    private static final String MESSAGE_STORAGE_FILE_CREATED = "Created new empty storage file: %1$s";
    private static final String MESSAGE_WELCOME = "Welcome to your Address Book!";
    private static final String MESSAGE_USING_DEFAULT_FILE = "Using default storage file : " + DEFAULT_STORAGE_FILEPATH;

    // These are the prefix strings to define the data type of a command parameter
    private static final String PERSON_DATA_PREFIX_PHONE = "p/";
    private static final String PERSON_DATA_PREFIX_EMAIL = "e/";

    private static final String PERSON_STRING_REPRESENTATION = "%1$s " // name
                                                            + PERSON_DATA_PREFIX_PHONE + "%2$s " // phone
                                                            + PERSON_DATA_PREFIX_EMAIL + "%3$s"; // email
    private static final String COMMAND_WORD_ADD = "add";
    private static final String COMMAND_ADD_DESC = "Adds a person to the address book.";
    private static final String COMMAND_ADD_PARAMETERS = "NAME "
                                                      + PERSON_DATA_PREFIX_PHONE + "PHONE_NUMBER "
                                                      + PERSON_DATA_PREFIX_EMAIL + "EMAIL";
    private static final String COMMAND_ADD_EXAMPLE = COMMAND_WORD_ADD + " John Doe p/98765432 e/johnd@gmail.com";

    private static final String COMMAND_WORD_FIND = "find";
    private static final String COMMAND_FIND_DESC = "Finds all persons whose names contain any of the specified "
                                        + "keywords (case-sensitive) and displays them as a list with index numbers.";
    private static final String COMMAND_FIND_PARAMETERS = "KEYWORD [MORE_KEYWORDS]";
    private static final String COMMAND_FIND_EXAMPLE = COMMAND_WORD_FIND + " alice bob charlie";

    private static final String COMMAND_WORD_LIST = "list";
    private static final String COMMAND_LIST_DESC = "Displays all persons as a list with index numbers.";
    private static final String COMMAND_LIST_EXAMPLE = COMMAND_WORD_LIST;

    private static final String COMMAND_WORD_DELETE = "delete";
    private static final String COMMAND_DELETE_DESC = "Deletes a person identified by the index number used in "
                                                    + "the last find/list call.";
    private static final String COMMAND_DELETE_PARAMETER = "INDEX";
    private static final String COMMAND_DELETE_EXAMPLE = COMMAND_WORD_DELETE + " 1";

    private static final String COMMAND_WORD_CLEAR = "clear";
    private static final String COMMAND_CLEAR_DESC = "Clears address book permanently.";
    private static final String COMMAND_CLEAR_EXAMPLE = COMMAND_WORD_CLEAR;

    private static final String COMMAND_WORD_HELP = "help";
    private static final String COMMAND_HELP_DESC = "Shows program usage instructions.";
    private static final String COMMAND_HELP_EXAMPLE = COMMAND_WORD_HELP;

    private static final String COMMAND_WORD_EXIT = "exit";
    private static final String COMMAND_EXIT_DESC = "Exits the program.";
    private static final String COMMAND_EXIT_EXAMPLE = COMMAND_WORD_EXIT;

    private static final String DIVIDER = "===================================================";


    /* We use a String array to store details of a single person.
     * The constants given below are the indexes for the different data elements of a person
     * used by the internal String[] storage format.
     * For example, a person's name is stored as the 0th element in the array.
     */
    private static final int PERSON_DATA_INDEX_NAME = 0;
    private static final int PERSON_DATA_INDEX_PHONE = 1;
    private static final int PERSON_DATA_INDEX_EMAIL = 2;

    /**
     * The number of data elements for a single person.
     */
    private static final int PERSON_DATA_COUNT = 3;

    /**
     * Offset required to convert between 1-indexing and 0-indexing.COMMAND_
     */
    private static final int DISPLAYED_INDEX_OFFSET = 1;



    /**
     * If the first non-whitespace character in a user's input line is this, that line will be ignored.
     */
    private static final char INPUT_COMMENT_MARKER = '#';

    /*
     * This variable is declared for the whole class (instead of declaring it
     * inside the readUserCommand() method to facilitate automated testing using
     * the I/O redirection technique. If not, only the first line of the input
     * text file will be processed.
     */
    private static final Scanner SCANNER = new Scanner(System.in);
    /*
     * ==============NOTE TO STUDENTS======================================================================
     * Note that the type of the variable below can also be declared as List<String[]>, as follows:
     *    private static final List<String[]> ALL_PERSONS = new ArrayList<>()
     * That is because List is an interface implemented by the ArrayList class.
     * In this code we use ArrayList instead because we wanted to to stay away from advanced concepts
     * such as interface inheritance.
     * ====================================================================================================
     */
    /**
     * List of all persons in the address book.
     */
    private static final ArrayList<String[]> ALL_PERSONS = new ArrayList<>();


    /**
     * Stores the most recent list of persons shown to the user as a result of a user command.
     * This is a subset of the full list. Deleting persons in the pull list does not delete
     * those persons from this list.
     */
    private static ArrayList<String[]> latestPersonListingView = getAllPersonsInAddressBook(); // initial view is of all

    /**
     * Latest command entered by the user.
     */
    private static String userCommand;

    /**
     * The path to the file used for storing person data.
     */
    private static String storageFilePath;

    /*
     * ==============NOTE TO STUDENTS======================================
     * Notice how this method solves the whole problem at a very high level.
     * We can understand the high-level logic of the program by reading this
     * method alone.
     * ====================================================================
     */
    public static void main(String[] args) {
        showWelcomeMessage();
        processProgramArgs(args);
        loadDataFromStorage();
        while (true) {
            userCommand = getUserInput();
            echoUserCommand(userCommand);
            String feedback = executeCommand(userCommand);
            showResultToUser(feedback);
        }
    }

    /*
     * ==============NOTE TO STUDENTS======================================
     * The method header comment can be omitted if the method is trivial
     * and the header comment is going to be almost identical to the method
     * signature anyway.
     * ====================================================================
     */
    private static void showWelcomeMessage() {
        showToUser(DIVIDER, DIVIDER, VERSION, MESSAGE_WELCOME, DIVIDER);
    }

    private static void showResultToUser(String result) {
        showToUser(result, DIVIDER);
    }

    /*
     * ==============NOTE TO STUDENTS======================================
     * Parameter description can be omitted from the method header comment
     * if the parameter name is self-explanatory.
     * In the method below, '@param userInput' comment has been omitted.
     * ====================================================================
     */
    /**
     * Echoes the user input back to the user.
     */
    private static void echoUserCommand(String userCommand) {
        showToUser("[Command entered:" + userCommand + "]");
    }

    /*
     * ==============NOTE TO STUDENTS==========================================
     * If the reader wants a deeper understanding of the solution, she can go
     * to the next level of abstraction by reading the methods (given below)
     * that is referenced by the method above.
     * ====================================================================
     */

    /**
     * Processes the program main method run arguments.
     * If a valid storage file is specified, sets up that file for storage.
     * Otherwise sets up the default file for storage.
     * 
     * @param args full program arguments passed to application main method
     */
    private static void processProgramArgs(String[] args) {
        if (args.length >= 2) {
            showToUser(MESSAGE_INVALID_PROGRAM_ARGS);
            exitProgram();
        }

        if (args.length == 1) {
            setupGivenFileForStorage(args[0]);
        }

        if(args.length == 0) {
            setupDefaultFileForStorage();
        }
    }

    /**
     * Sets up the storage file based on the supplied file path.
     * Creates the file if it is missing.
     * Exits if the file name is not acceptable.
     */
    private static void setupGivenFileForStorage(String filePath) {

        if (!isValidFilePath(filePath)) {
            showToUser(String.format(MESSAGE_INVALID_FILE, filePath));
            exitProgram();
        }

        storageFilePath = filePath;
        createFileIfMissing(filePath);
    }

    /**
     * Displays the goodbye message and exits the runtime.
     */
    private static void exitProgram() {
        showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
        System.exit(0);
    }

    /**
     * Sets up the storage based on the default file.
     * Creates file if missing.
     * Exits program if the file cannot be created.
     */
    private static void setupDefaultFileForStorage() {
        showToUser(MESSAGE_USING_DEFAULT_FILE);
        storageFilePath = DEFAULT_STORAGE_FILEPATH;
        createFileIfMissing(storageFilePath);
    }

    /**
     * Returns true if the given file is acceptable.
     * The file path is acceptable if it ends in '.txt'
     * TODO: Implement a more rigorous validity checking.
     */
    private static boolean isValidFilePath(String filePath) {
        return filePath.endsWith(".txt");
    }

    /**
     * Initialises the in-memory data using the storage file.
     * Assumption: The file exists.
     */
    private static void loadDataFromStorage() {
        initialiseAddressBookModel(loadPersonsFromFile(storageFilePath));
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
    public static String executeCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case COMMAND_WORD_ADD:
            return executeAddPerson(commandArgs);
        case COMMAND_WORD_FIND:
            return executeFindPersons(commandArgs);
        case COMMAND_WORD_LIST:
            return executeListAllPersonsInAddressBook();
        case COMMAND_WORD_DELETE:
            return executeDeletePerson(commandArgs);
        case COMMAND_WORD_CLEAR:
            return executeClearAddressBook();
        case COMMAND_WORD_HELP:
            return getUsageInfoForAllCommands();
        case COMMAND_WORD_EXIT:
            executeExitProgramRequest();
        default:
            return getMessageForInvalidCommandInput(getUsageInfoForAllCommands());
        }
    }

    /**
     * Splits raw user input into command word and command arguments string
     *
     * @return  size 2 array; first element is the command type and second element is the arguments string
     */
    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split =  rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" }; // else case: no parameters
    }

    /**
     * Constructs a generic feedback message for an invalid command from user, with instructions for correct usage.
     *
     * @param correctUsageInfo message showing the correct usage
     * @return invalid command args feedback message
     */
    private static String getMessageForInvalidCommandInput(String correctUsageInfo) {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, userCommand, correctUsageInfo);
    }

    /**
     * Adds a person (specified by the command args) to the address book.
     * The entire command arguments string is treated as a string representation of the person to add.
     *
     * @param commandArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private static String executeAddPerson(String commandArgs) {
        // try decoding a person from the raw args
        final Optional<String[]> decodeResult = decodePersonFromString(commandArgs);

        // checks if args are valid (decode result will not be present if the person is invalid)
        if (!decodeResult.isPresent()) {
            return getMessageForInvalidCommandInput(getUsageInfoForAddCommand());
        }

        // add the person as specified
        final String[] personToAdd = decodeResult.get();
        addPersonToAddressBook(personToAdd);
        return getMessageForSuccessfulAddPerson(personToAdd);
    }

    /**
     * Constructs a feedback message for a successful add person command execution.
     *
     * @see #executeAddPerson(String)
     * @param addedPerson person who was successfully added
     * @return successful add person feedback message
     */
    private static String getMessageForSuccessfulAddPerson(String[] addedPerson) {
        return String.format(MESSAGE_ADDED,
                getNameFromPerson(addedPerson), getPhoneFromPerson(addedPerson), getEmailFromPerson(addedPerson));
    }

    /**
     * Finds and lists all persons in address book whose name contains any of the argument keywords.
     * Keyword matching is case sensitive.
     * 
     * @param commandArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private static String executeFindPersons(String commandArgs) {
        final Set<String> keywords = extractKeywordsFromFindPersonArgs(commandArgs);
        final ArrayList<String[]> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        showToUser(personsFound);
        return getMessageForPersonsDisplayedSummary(personsFound);
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    private static String getMessageForPersonsDisplayedSummary(ArrayList<String[]> personsDisplayed) {
        return String.format(MESSAGE_PERSONS_FOUND_OVERVIEW, personsDisplayed.size());
    }

    /**
     * Extract keywords from the command arguments given for the find persons command.
     *
     * @param findPersonCommandArgs full command args string for the find persons command
     * @return set of keywords as specified by args
     */
    private static Set<String> extractKeywordsFromFindPersonArgs(String findPersonCommandArgs) {
        return new HashSet<>(splitByWhitespace(findPersonCommandArgs.trim()));
    }

    /**
     * Retrieve all persons in the full model whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons in full model with name containing some of the keywords
     */
    private static ArrayList<String[]> getPersonsWithNameContainingAnyKeyword(Collection<String> keywords) {
        final ArrayList<String[]> matchedPersons = new ArrayList<>();
        for (String[] person : getAllPersonsInAddressBook()) {
            final Set<String> wordsInName = new HashSet<>(splitByWhitespace(getNameFromPerson(person)));
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

    /**
     * Deletes person identified using last displayed index.
     * 
     * @param commandArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private static String executeDeletePerson(String commandArgs) {
        if (!isDeletePersonArgsValid(commandArgs)) {
            return getMessageForInvalidCommandInput(getUsageInfoForDeleteCommand());
        }
        final int targetVisibleIndex = extractTargetIndexFromDeletePersonArgs(commandArgs);
        if (!isDisplayIndexValidForLastPersonListingView(targetVisibleIndex)) {
            return MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        }
        final String[] targetInModel = getPersonByLastVisibleIndex(targetVisibleIndex);
        return deletePersonFromAddressBook(targetInModel) ? getMessageForSuccessfulDelete(targetInModel) // success
                                                          : MESSAGE_PERSON_NOT_IN_ADDRESSBOOK; // not found
    }

    /**
     * Checks validity of delete person argument string's format.
     *
     * @param rawArgs raw command args string for the delete person command
     * @return whether the input args string is valid
     */
    private static boolean isDeletePersonArgsValid(String rawArgs) {
        try {
            final int extractedIndex = Integer.parseInt(rawArgs.trim()); // use standard libraries to parse
            return extractedIndex >= DISPLAYED_INDEX_OFFSET;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Extracts the target's index from the raw delete person args string
     *
     * @param rawArgs raw command args string for the delete person command
     * @return extracted index
     */
    private static int extractTargetIndexFromDeletePersonArgs(String rawArgs) {
        return Integer.parseInt(rawArgs.trim());
    }

    /**
     * Checks that the given index is within bounds and valid for the last shown person list view.
     *
     * @param index to check
     * @return whether it is valid
     */
    private static boolean isDisplayIndexValidForLastPersonListingView(int index) {
        return index >= DISPLAYED_INDEX_OFFSET && index < getLatestPersonListingView().size() + DISPLAYED_INDEX_OFFSET;
    }

    /**
     * Constructs a feedback message for a successful delete person command execution.
     *
     * @see #executeDeletePerson(String)
     * @param deletedPerson successfully deleted
     * @return successful delete person feedback message
     */
    private static String getMessageForSuccessfulDelete(String[] deletedPerson) {
        return String.format(MESSAGE_DELETE_PERSON_SUCCESS, getMessageForFormattedPersonData(deletedPerson));
    }

    /**
     * Clears all persons in the address book.
     * 
     * @return feedback display message for the operation result
     */
    private static String executeClearAddressBook() {
        clearAddressBook();
        return MESSAGE_ADDRESSBOOK_CLEARED;
    }

    /**
     * Displays all persons in the address book to the user; in added order.
     *
     * @return feedback display message for the operation result
     */
    private static String executeListAllPersonsInAddressBook() {
        ArrayList<String[]> toBeDisplayed = getAllPersonsInAddressBook();
        showToUser(toBeDisplayed);
        return getMessageForPersonsDisplayedSummary(toBeDisplayed);
    }

    /**
     * Request to terminate the program.
     *
     * @return feedback display message for the operation result
     */
    private static void executeExitProgramRequest() {
        exitProgram();
    }

    /*
     * ===========================================
     *               UI LOGIC
     * ===========================================
     */

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores lines with first non-whitespace char equal to {@link #INPUT_COMMENT_MARKER} (considered comments)
     *
     * @return full line entered by the user
     */
    private static String getUserInput() {
        System.out.print(LINE_PREFIX + "Enter command: ");
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty() || inputLine.trim().charAt(0) == INPUT_COMMENT_MARKER) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }

   /* ==============NOTE TO STUDENTS======================================
    * Note how the method below uses Java 'Varargs' feature so that the
    * method can accept a varying number of message parameters.
    * ====================================================================
    */
    /**
     * Shows a message to the user
     */
    private static void showToUser(String... message) {
        for (String m : message) {
            System.out.println(LINE_PREFIX + m);
        }
    }

    /**
     * Shows the list of persons to the user.
     * The list will be indexed, starting from 1.
     *
     */
    private static void showToUser(ArrayList<String[]> persons) {
        String listAsString = getDisplayString(persons);
        showToUser(listAsString);
        updateLatestViewedPersonListing(persons);
    }

    /**
     * Returns the display string representation of the list of persons.
     */
    private static String getDisplayString(ArrayList<String[]> persons) {
        final StringBuilder messageAccumulator = new StringBuilder();
        for (int i = 0; i < persons.size(); i++) {
            final String[] person = persons.get(i);
            final int displayIndex = i + DISPLAYED_INDEX_OFFSET;
            messageAccumulator.append('\t')
                              .append(getIndexedPersonListElementMessage(displayIndex, person))
                              .append(LS);
        }
        return messageAccumulator.toString();
    }

    /**
     * Constructs a prettified listing element message to represent a person and their data.
     *
     * @param visibleIndex visible index for this listing
     * @param person to show
     * @return formatted listing message with index
     */
    private static String getIndexedPersonListElementMessage(int visibleIndex, String[] person) {
        return String.format(MESSAGE_DISPLAY_LIST_ELEMENT_INDEX, visibleIndex) + getMessageForFormattedPersonData(person);
    }

    /**
     * Constructs a prettified string to show the user a person's data.
     *
     * @param person to show
     * @return formatted message showing internal state
     */
    private static String getMessageForFormattedPersonData(String[] person) {
        return String.format(MESSAGE_DISPLAY_PERSON_DATA,
                getNameFromPerson(person), getPhoneFromPerson(person), getEmailFromPerson(person));
    }

    /**
     * Updates the latest person listing view the user has seen.
     *
     * @param newListing the new listing of persons
     */
    private static void updateLatestViewedPersonListing(ArrayList<String[]> newListing) {
        // clone to insulate from future changes to arg list
        latestPersonListingView = new ArrayList<>(newListing);
    }

    /**
     * Retrieves the person identified by the displayed index from the last shown listing of persons.
     *
     * @param lastVisibleIndex displayed index from last shown person listing
     * @return the actual person object in the last shown person listing
     */
    private static String[] getPersonByLastVisibleIndex(int lastVisibleIndex) {
       return latestPersonListingView.get(lastVisibleIndex - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * @return unmodifiable list view of the last person listing view
     */
    private static ArrayList<String[]> getLatestPersonListingView() {
        return latestPersonListingView;
    }


    /*
     * ===========================================
     *             STORAGE LOGIC
     * ===========================================
     */

    /**
     * Creates storage file if it does not exist. Shows feedback to user.
     *
     * @param filePath file to create if not present
     */
    private static void createFileIfMissing(String filePath) {
        final File storageFile = new File(filePath);
        if (storageFile.exists()) {
            return;
        }

        showToUser(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, filePath));

        try {
            storageFile.createNewFile();
            showToUser(String.format(MESSAGE_STORAGE_FILE_CREATED, filePath));
        } catch (IOException ioe) {
            showToUser(String.format(MESSAGE_ERROR_CREATING_STORAGE_FILE, filePath));
            exitProgram();
        }
    }

    /**
     * Converts contents of a file into a list of persons.
     * Shows error messages and exits program if any errors in reading or decoding was encountered.
     *
     * @param filePath file to load from
     * @return the list of decoded persons
     */
    private static ArrayList<String[]> loadPersonsFromFile(String filePath) {
        final Optional<ArrayList<String[]>> successfullyDecoded = decodePersonsFromStrings(getLinesInFile(filePath));
        if (!successfullyDecoded.isPresent()) {
            showToUser(MESSAGE_INVALID_STORAGE_FILE_CONTENT);
            exitProgram();
        }
        return successfullyDecoded.get();
    }

    /**
     * Gets all lines in the specified file as a list of strings. Line separators are removed.
     * Shows error messages and exits program if unable to read from file.
     */
    private static ArrayList<String> getLinesInFile(String filePath) {
        ArrayList<String> lines = null;
        try {
            lines = new ArrayList(Files.readAllLines(Paths.get(filePath)));
        } catch (FileNotFoundException fnfe) {
            showToUser(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, filePath));
            exitProgram();
        } catch (IOException ioe) {
            showToUser(String.format(MESSAGE_ERROR_READING_FROM_FILE, filePath));
            exitProgram();
        }
        return lines;
    }

    /**
     * Saves all data to the file.
     * Exits program if there is an error saving to file.
     *
     * @param filePath file for saving
     */
    private static void savePersonsToFile(ArrayList<String[]> persons, String filePath) {
        final ArrayList<String> linesToWrite = encodePersonsToStrings(persons);
        try {
            Files.write(Paths.get(storageFilePath), linesToWrite);
        } catch (IOException ioe) {
            showToUser(String.format(MESSAGE_ERROR_WRITING_TO_FILE, filePath));
            exitProgram();
        }
    }


    /*
     * ================================================================================
     *        INTERNAL ADDRESS BOOK DATA METHODS
     * ================================================================================
     */

    /**
     * Adds a person to the address book. Saves changes to storage file.
     *
     * @param person to add
     */
    private static void addPersonToAddressBook(String[] person) {
        ALL_PERSONS.add(person);
        savePersonsToFile(getAllPersonsInAddressBook(), storageFilePath);
    }

    /**
     * Deletes a person from the address book, target is identified by it's absolute index in the full list.
     * Saves changes to storage file.
     *
     * @param index absolute index of person to delete (index within {@link #ALL_PERSONS})
     */
    private static void deletePersonFromAddressBook(int index) {
        ALL_PERSONS.remove(index);
        savePersonsToFile(getAllPersonsInAddressBook(), storageFilePath);
    }

    /**
     * Deletes the specified person from the addressbook if it is inside. Saves any changes to storage file.
     *
     * @param exactPerson the actual person inside the address book (exactPerson == the person to delete in the full list)
     * @return true if the given person was found and deleted in the model
     */
    private static boolean deletePersonFromAddressBook(String[] exactPerson) {
        final boolean changed = ALL_PERSONS.remove(exactPerson);
        if (changed) {
            savePersonsToFile(getAllPersonsInAddressBook(), storageFilePath);
        }
        return changed;
    }

    /**
     * @return unmodifiable list view of all persons in the address book
     */
    private static ArrayList<String[]> getAllPersonsInAddressBook() {
        return ALL_PERSONS;
    }

    /**
     * Clears all persons in the address book and saves changes to file.
     */
    private static void clearAddressBook() {
        ALL_PERSONS.clear();
        savePersonsToFile(getAllPersonsInAddressBook(), storageFilePath);
    }

    /**
     * Resets the internal model with the given data. Does not save to file.
     *
     * @param persons list of persons to initialise the model with
     */
    private static void initialiseAddressBookModel(ArrayList<String[]> persons) {
        ALL_PERSONS.clear();
        ALL_PERSONS.addAll(persons);
    }

    
    /*
     * ===========================================
     *             PERSON METHODS
     * ===========================================
     */

    /**
     * @param person whose name you want
     * @return person's name
     */
    private static String getNameFromPerson(String[] person) {
        return person[PERSON_DATA_INDEX_NAME];
    }

    /**
     * @param person whose phone number you want
     * @return person's phone number
     */
    private static String getPhoneFromPerson(String[] person) {
        return person[PERSON_DATA_INDEX_PHONE];
    }

    /**
     * @param person whose email you want
     * @return person's email
     */
    private static String getEmailFromPerson(String[] person) {
        return person[PERSON_DATA_INDEX_EMAIL];
    }

    /**
     * Create a person for use in the internal data.
     *
     * @param name of person
     * @param phone without data prefix
     * @param email without data prefix
     * @return constructed person
     */
    private static String[] makePersonFromData(String name, String phone, String email) {
        final String[] person = new String[PERSON_DATA_COUNT];
        person[PERSON_DATA_INDEX_NAME] = name;
        person[PERSON_DATA_INDEX_PHONE] = phone;
        person[PERSON_DATA_INDEX_EMAIL] = email;
        return person;
    }

    /**
     * Encodes a person into a decodable and readable string representation.
     *
     * @param person to be encoded
     * @return encoded string
     */
    private static String encodePersonToString(String[] person) {
        return String.format(PERSON_STRING_REPRESENTATION,
                getNameFromPerson(person), getPhoneFromPerson(person), getEmailFromPerson(person));
    }

    /**
     * Encodes list of persons into list of decodable and readable string representations.
     *
     * @param persons to be encoded
     * @return encoded strings
     */
    private static ArrayList<String> encodePersonsToStrings(ArrayList<String[]> persons) {
        final ArrayList<String> encoded = new ArrayList<>();
        for (String[] person : persons) {
            encoded.add(encodePersonToString(person));
        }
        return encoded;
    }

    /*
     * ==============NOTE TO STUDENTS======================================
     * Note the use of Java's new 'Optional' feature to indicate that
     * the return value may not always be present.
     * ====================================================================
     */
    /**
     * Decodes a person from it's supposed string representation.
     *
     * @param encoded string to be decoded
     * @return if cannot decode: empty Optional
     *         else: Optional containing decoded person
     */
    private static Optional<String[]> decodePersonFromString(String encoded) {
        // check that we can extract the parts of a person from the encoded string
        if (!isPersonDataExtractableFrom(encoded)) {
            return Optional.empty();
        }
        final String[] decodedPerson = makePersonFromData(
                extractNameFromPersonString(encoded),
                extractPhoneFromPersonString(encoded),
                extractEmailFromPersonString(encoded)
        );
        // check that the constructed person is valid
        return isPersonDataValid(decodedPerson) ? Optional.of(decodedPerson) : Optional.empty();
    }

    /**
     * Decode persons from a list of string representations.
     *
     * @param encodedPersons strings to be decoded
     * @return if cannot decode any: empty Optional
     *         else: Optional containing decoded persons
     */
    private static Optional<ArrayList<String[]>> decodePersonsFromStrings(ArrayList<String> encodedPersons) {
        final ArrayList<String[]> decodedPersons = new ArrayList<>();
        for (String encodedPerson : encodedPersons) {
            final Optional<String[]> decodedPerson = decodePersonFromString(encodedPerson);
            if (!decodedPerson.isPresent()) {
                return Optional.empty();
            }
            decodedPersons.add(decodedPerson.get());
        }
        return Optional.of(decodedPersons);
    }

    /**
     * Checks whether person data (email, name, phone etc) can be extracted from the argument string.
     * Format is [name] p/[phone] e/[email], phone and email positions can be swapped.
     *
     * @param personData person string representation
     * @return whether format of add command arguments allows parsing into individual arguments
     */
    private static boolean isPersonDataExtractableFrom(String personData) {
        final String matchAnyPersonDataPrefix = PERSON_DATA_PREFIX_PHONE + '|' + PERSON_DATA_PREFIX_EMAIL;
        final String[] splitArgs = personData.trim().split(matchAnyPersonDataPrefix);
        return splitArgs.length == 3 // 3 arguments
                && !splitArgs[0].isEmpty() // non-empty arguments
                && !splitArgs[1].isEmpty()
                && !splitArgs[2].isEmpty();
    }

    /**
     * Extracts substring representing person name from person string representation
     *
     * @param encoded person string representation
     * @return name argument
     */
    private static String extractNameFromPersonString(String encoded) {
        final int indexOfPhonePrefix = encoded.indexOf(PERSON_DATA_PREFIX_PHONE);
        final int indexOfEmailPrefix = encoded.indexOf(PERSON_DATA_PREFIX_EMAIL);
        // name is leading substring up to first data prefix symbol
        int indexOfFirstPrefix = Math.min(indexOfEmailPrefix, indexOfPhonePrefix);
        return encoded.substring(0, indexOfFirstPrefix).trim();
    }

    /**
     * Extracts substring representing phone number from person string representation
     *
     * @param encoded person string representation
     * @return phone number argument WITHOUT prefix
     */
    private static String extractPhoneFromPersonString(String encoded) {
        final int indexOfPhonePrefix = encoded.indexOf(PERSON_DATA_PREFIX_PHONE);
        final int indexOfEmailPrefix = encoded.indexOf(PERSON_DATA_PREFIX_EMAIL);

        // phone is last arg, target is from prefix to end of string
        if (indexOfPhonePrefix > indexOfEmailPrefix) {
            return removePrefixSign(encoded.substring(indexOfPhonePrefix, encoded.length()).trim(),
                    PERSON_DATA_PREFIX_PHONE);

        // phone is middle arg, target is from own prefix to next prefix
        } else {
            return removePrefixSign(
                    encoded.substring(indexOfPhonePrefix, indexOfEmailPrefix).trim(),
                    PERSON_DATA_PREFIX_PHONE);
        }
    }

    /**
     * Extracts substring representing email from person string representation
     *
     * @param encoded person string representation
     * @return email argument WITHOUT prefix
     */
    private static String extractEmailFromPersonString(String encoded) {
        final int indexOfPhonePrefix = encoded.indexOf(PERSON_DATA_PREFIX_PHONE);
        final int indexOfEmailPrefix = encoded.indexOf(PERSON_DATA_PREFIX_EMAIL);

        // email is last arg, target is from prefix to end of string
        if (indexOfEmailPrefix > indexOfPhonePrefix) {
            return removePrefixSign(encoded.substring(indexOfEmailPrefix, encoded.length()).trim(),
                    PERSON_DATA_PREFIX_EMAIL);

        // email is middle arg, target is from own prefix to next prefix
        } else {
            return removePrefixSign(
                    encoded.substring(indexOfEmailPrefix, indexOfPhonePrefix).trim(),
                    PERSON_DATA_PREFIX_EMAIL);
        }
    }

    /**
     * Validates a person's data fields
     *
     * @param person String array representing the person (used in internal data)
     * @return whether the given person has valid data
     */
    private static boolean isPersonDataValid(String[] person) {
        return isPersonNameValid(person[PERSON_DATA_INDEX_NAME])
                && isPersonPhoneValid(person[PERSON_DATA_INDEX_PHONE])
                && isPersonEmailValid(person[PERSON_DATA_INDEX_EMAIL]);
    }

    /*
     * ==============NOTE TO STUDENTS======================================
     * Note the use of 'regular expressions' in the method below.
     * Regular expressions can be very useful in checking if a a string
     * follows a sepcific format.
     * ====================================================================
     */
    /**
     * Validates string as a legal person name
     *
     * @param name to be validated
     * @return whether arg is a valid person name
     */
    private static boolean isPersonNameValid(String name) {
        return name.matches("(\\w|\\s)+");  // name is nonempty mixture of alphabets and whitespace
        //TODO: implement a more permissive validation
    }

    /**
     * Validates string as a legal person phone number
     *
     * @param phone to be validated
     * @return whether arg is a valid person phone number
     */
    private static boolean isPersonPhoneValid(String phone) {
        return phone.matches("\\d+");    // phone nonempty sequence of digits
        //TODO: implement a more permissive validation
    }

    /**
     * Validates string as a legal person email
     *
     * @param email to be validated
     * @return whether arg is a valid person email
     */
    private static boolean isPersonEmailValid(String email) {
        return email.matches("\\S+@\\S+\\.\\S+"); // email is [non-whitespace]@[non-whitespace].[non-whitespace]
        //TODO: implement a more permissive validation
    }


    /*
     * ===============================================
     *         COMMAND HELP INFO FOR USERS
     * ===============================================
     */

    /**
     * @return  Usage info for all commands
     */
    private static String getUsageInfoForAllCommands() {
        return getUsageInfoForAddCommand() + LS
                + getUsageInfoForFindCommand() + LS
                + getUsageInfoForViewCommand() + LS
                + getUsageInfoForDeleteCommand() + LS
                + getUsageInfoForClearCommand() + LS
                + getUsageInfoForExitCommand() + LS
                + getUsageInfoForHelpCommand();
    }

    /**
     * Builds string for showing 'add' command usage instruction
     * 
     * @return  'add' command usage instruction
     */
    private static String getUsageInfoForAddCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_ADD, COMMAND_ADD_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_ADD_PARAMETERS) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_ADD_EXAMPLE) + LS;
    }

    /**
     * Builds string for showing 'find' command usage instruction
     *
     * @return  'find' command usage instruction
     */
    private static String getUsageInfoForFindCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_FIND, COMMAND_FIND_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_FIND_PARAMETERS) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_FIND_EXAMPLE) + LS;
    }

    /**
     * Builds string for showing 'delete' command usage instruction
     *
     * @return  'delete' command usage instruction
     */
    private static String getUsageInfoForDeleteCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_DELETE, COMMAND_DELETE_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DELETE_PARAMETER) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE) + LS;
    }

    /**
     * Builds string for showing 'clear' command usage instruction
     *
     * @return  'clear' command usage instruction
     */
    private static String getUsageInfoForClearCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_CLEAR, COMMAND_CLEAR_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_CLEAR_EXAMPLE) + LS;
    }

    /**
     * Builds string for showing 'view' command usage instruction
     *
     * @return  'view' command usage instruction
     */
    private static String getUsageInfoForViewCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_LIST, COMMAND_LIST_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_LIST_EXAMPLE) + LS;
    }

    /**
     * Builds string for showing 'help' command usage instruction
     *
     * @return  'help' command usage instruction
     */
    private static String getUsageInfoForHelpCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_HELP, COMMAND_HELP_DESC)
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_HELP_EXAMPLE);
    }

    /**
     * Builds string for showing 'exit' command usage instruction
     *
     * @return  'exit' command usage instruction
     */
    private static String getUsageInfoForExitCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_EXIT, COMMAND_EXIT_DESC)
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EXIT_EXAMPLE);
    }


    /*
     * ============================
     *         UTILITY METHODS
     * ============================
     */

    /**
     * Removes sign(p/, d/, etc) from parameter string
     * 
     * @param s  Parameter as a string
     * @param sign  Parameter sign to be removed
     * 
     * @return  Priority string without p/
     */
    private static String removePrefixSign(String s, String sign) {
        return s.replace(sign, "");
    }

    /**
     * Splits a source string into the list of substrings that were separated by whitespace.
     *
     * @param toSplit source string
     * @return split by whitespace
     */
    private static ArrayList<String> splitByWhitespace(String toSplit) {
        return new ArrayList(Arrays.asList(toSplit.trim().split("\\s+")));
    }

}