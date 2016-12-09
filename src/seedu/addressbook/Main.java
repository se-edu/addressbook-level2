package seedu.addressbook;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.storage.StorageFile.*;

import seedu.addressbook.commands.*;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.parser.Parser.ParseException;
import seedu.addressbook.storage.StorageFile;
import seedu.addressbook.ui.TextUi;

import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.addressbook.common.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;


/**
 * Entry point of the Address Book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    /** Version info of the program. */
    public static final String VERSION = "AddessBook Level 2 - Version 1.0";

    private TextUi ui;
    private StorageFile storage;
    private AddressBook addressBook;

    /** The list of person shown to the user most recently.  */
    private List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();


    public static void main(String... launchArgs) {
        new Main().run(launchArgs);
    }

    /** Runs the program until termination.  */
    public void run(String[] launchArgs) {
        start(launchArgs);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     *
     * @param launchArgs arguments supplied by the user at program launch
     *
     */
    private void start(String[] launchArgs) {
        try {
            this.ui = new TextUi();
            this.storage = initializeStorage(launchArgs);
            this.addressBook = storage.load();
            ui.showWelcomeMessage(VERSION, storage.getPath());

        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.showInitFailedMessage();
            /*
             * ==============NOTE TO STUDENTS=========================================================================
             * We are throwing a RuntimeException which is an 'unchecked' exception. Unchecked exceptions do not need
             * to be declared in the method signature.
             * The reason we are using an unchecked exception here is because the caller cannot reasonably be expected
             * to recover from an exception.
             * Cf https://docs.oracle.com/javase/tutorial/essential/exceptions/runtime.html
             * =======================================================================================================
             */
            throw new RuntimeException(e);
        }
    }

    /** Prints the Goodbye message and exits. */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    private void runCommandLoopUntilExitCommand() {
        String userCommandText;
        String[] parsedCommand;
        String commandWord;
        String arguments;
        CommandResult result;
        Parser parser = new Parser();
        do {
            userCommandText = ui.getUserCommand();
            parsedCommand = parser.parseCommand(userCommandText);
            commandWord = parsedCommand[0];
            arguments = parsedCommand[1];

            switch(commandWord) {

                case AddCommand.COMMAND_WORD:
                    final Matcher addMatcher = Parser.PERSON_DATA_ARGS_FORMAT.matcher(arguments.trim());
                    IncorrectCommand incorrectAddCommand;
                    if (!addMatcher.matches()) {
                        incorrectAddCommand = new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                AddCommand.MESSAGE_USAGE));
                        result = incorrectAddCommand.execute();
                    } else {
                        try {
                            AddCommand addCommand = new AddCommand(
                                    addMatcher.group("name"),

                                    addMatcher.group("phone"),
                                    Parser.isPrivatePrefixPresent(addMatcher.group("isPhonePrivate")),

                                    addMatcher.group("email"),
                                    Parser.isPrivatePrefixPresent(addMatcher.group("isEmailPrivate")),

                                    addMatcher.group("address"),
                                    Parser.isPrivatePrefixPresent(addMatcher.group("isAddressPrivate")),

                                    Parser.getTagsFromArgs(addMatcher.group("tagArguments"))
                            );
                            addCommand.setData(addressBook, lastShownList);
                            result = addCommand.execute();
                        } catch (IllegalValueException ive) {
                            incorrectAddCommand = new IncorrectCommand(ive.getMessage());
                            result = incorrectAddCommand.execute();
                        }
                    }
                    break;

                case DeleteCommand.COMMAND_WORD:
                    try {
                        final int targetIndex = parser.parseArgsAsDisplayedIndex(arguments);
                        DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
                        deleteCommand.setData(addressBook, lastShownList);
                        result = deleteCommand.execute();
                        storage.save(addressBook);
                    } catch (ParseException pe) {
                        result = new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE)).execute();
                    } catch (NumberFormatException nfe) {
                        result =  new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX).execute();
                    } catch (Exception e) {
                        ui.showToUser(e.getMessage());
                        throw new RuntimeException(e);
                    }
                    break;

                case ClearCommand.COMMAND_WORD:
                    try {
                        ClearCommand clearCommand = new ClearCommand();
                        clearCommand.setData(addressBook, lastShownList);
                        result = clearCommand.execute();
                        storage.save(addressBook);
                    } catch (Exception e) {
                        ui.showToUser(e.getMessage());
                        throw new RuntimeException(e);
                    }
                    break;

                /* case FindCommand.COMMAND_WORD:
                    final Matcher findMatcher = Parser.KEYWORDS_ARGS_FORMAT.matcher(arguments.trim());
                    if (!findMatcher.matches()) {
                        result = new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                FindCommand.MESSAGE_USAGE)).execute();
                    } else {
                        final String[] keywords = findMatcher.group("keywords").split("\\s+");
                        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
                        FindCommand findCommand = new FindCommand(keywordSet);
                        result = findCommand.execute();
                    }
                    break;
                */

                case ListCommand.COMMAND_WORD:
                    ListCommand listCommand = new ListCommand();
                    try {
                        listCommand.setData(addressBook, lastShownList);
                        result = listCommand.execute();
                        storage.save(addressBook);
                    } catch (Exception e) {
                        ui.showToUser(e.getMessage());
                        throw new RuntimeException(e);
                    }
                    break;

                case ViewCommand.COMMAND_WORD:
                    try {
                        final int targetIndex = parser.parseArgsAsDisplayedIndex(arguments);
                        result = new ViewCommand(targetIndex).execute();
                    } catch (ParseException pe) {
                        result = new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                ViewCommand.MESSAGE_USAGE)).execute();
                    } catch (NumberFormatException nfe) {
                        result = new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX).execute();
                    }
                    break;

                case ViewAllCommand.COMMAND_WORD:
                    try {
                        final int targetIndex = parser.parseArgsAsDisplayedIndex(arguments);
                        result = new ViewAllCommand(targetIndex).execute();
                    } catch (ParseException pe) {
                        result = new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                                ViewAllCommand.MESSAGE_USAGE)).execute();
                    } catch (NumberFormatException nfe) {
                        result = new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX).execute();
                    }
                    break;

                case ExitCommand.COMMAND_WORD:
                    ExitCommand exitCommand = new ExitCommand();
                    try {
                        exitCommand.setData(addressBook, lastShownList);
                        result = exitCommand.execute();
                        storage.save(addressBook);
                    } catch (Exception e) {
                        ui.showToUser(e.getMessage());
                        throw new RuntimeException(e);
                    }
                    break;

                case HelpCommand.COMMAND_WORD:
                    HelpCommand helpCommand = new HelpCommand();
                    try {
                        helpCommand.setData(addressBook, lastShownList);
                        result = helpCommand.execute();
                        storage.save(addressBook);
                    } catch (Exception e) {
                        ui.showToUser(e.getMessage());
                        throw new RuntimeException(e);
                    }
                    break;

                default:
                    result = new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE)).execute();
            }

            recordResult(result);
            ui.showResultToUser(result);

        } while (!ExitCommand.isExit(commandWord));
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @param launchArgs arguments supplied by the user at program launch
     * @throws InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile initializeStorage(String[] launchArgs) throws InvalidStorageFilePathException {
        boolean isStorageFileSpecifiedByUser = launchArgs.length > 0;
        return isStorageFileSpecifiedByUser ? new StorageFile(launchArgs[0]) : new StorageFile();
    }


}