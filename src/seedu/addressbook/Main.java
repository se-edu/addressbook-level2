package seedu.addressbook;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import seedu.addressbook.commands.AddCommand;
import seedu.addressbook.commands.ClearCommand;
import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.commands.DeleteCommand;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.commands.FindCommand;
import seedu.addressbook.commands.HelpCommand;
import seedu.addressbook.commands.IncorrectCommand;
import seedu.addressbook.commands.ListCommand;
import seedu.addressbook.commands.ViewAllCommand;
import seedu.addressbook.commands.ViewCommand;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.parser.ParsedInput;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;
import seedu.addressbook.storage.StorageFile.InvalidStorageFilePathException;
import seedu.addressbook.storage.StorageFile.StorageOperationException;
import seedu.addressbook.ui.TextUi;


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
        ParsedInput parsedInput;
        do {
            String userCommandText = ui.getUserCommand();
            parsedInput = new Parser().parseCommand(userCommandText);
            CommandResult result = executeCommand(parsedInput);
            recordResult(result);
            ui.showResultToUser(result);

        } while (!parsedInput.getCommandType().equals(ExitCommand.COMMAND_WORD));
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }

    /**
     * Executes the command and returns the result.
     *
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(ParsedInput parsedInput)  {
        String cmdType = parsedInput.getCommandType();
        try {
            switch (cmdType) {
            case AddCommand.COMMAND_WORD:
                return executeAddCommand(parsedInput);
            case DeleteCommand.COMMAND_WORD:
                return executeDeleteCommand(parsedInput);
            case ClearCommand.COMMAND_WORD:
                return new ClearCommand().execute(addressBook);
            case FindCommand.COMMAND_WORD:
                return executeFindCommand(parsedInput);
            case ListCommand.COMMAND_WORD:
                return new ListCommand().execute(addressBook);
            case ViewCommand.COMMAND_WORD:
                return executeViewCommand(parsedInput);
            case ViewAllCommand.COMMAND_WORD:
                return executeViewAllCommand(parsedInput);
            case ExitCommand.COMMAND_WORD:
                return new ExitCommand().execute();
            case HelpCommand.COMMAND_WORD:
                return new HelpCommand().execute();
            case ParsedInput.INCORRECT_COMMAND:
                return executeIncorrectCommand(parsedInput);
            default:
                throw new AssertionError("Missing support for command!");
            }
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private CommandResult executeAddCommand(ParsedInput parsedInput) throws IllegalValueException, StorageOperationException {
        AddCommand command = new AddCommand(parsedInput.getName(),
                parsedInput.getPhone(), parsedInput.getIsPhonePrivate(),
                parsedInput.getEmail(), parsedInput.getIsEmailPrivate(),
                parsedInput.getAddress(), parsedInput.getIsAddressPrivate(),
                parsedInput.getTags());
        CommandResult result = command.execute(addressBook);
        storage.save(addressBook);
        return result;
    }

    private CommandResult executeDeleteCommand(ParsedInput parsedInput) throws StorageOperationException {
        DeleteCommand command = new DeleteCommand(parsedInput.getTargetVisibleIndex());
        CommandResult result = command.execute(addressBook, lastShownList);
        storage.save(addressBook);
        return result;
    }

    private CommandResult executeFindCommand(ParsedInput parsedInput) throws StorageOperationException {
        FindCommand command = new FindCommand(parsedInput.getKeywords());
        CommandResult result = command.execute(addressBook);
        storage.save(addressBook);
        return result;
    }

    private CommandResult executeViewCommand(ParsedInput parsedInput) throws StorageOperationException {
        ViewCommand command = new ViewCommand(parsedInput.getTargetVisibleIndex());
        CommandResult result = command.execute(addressBook, lastShownList);
        storage.save(addressBook);
        return result;
    }

    private CommandResult executeViewAllCommand(ParsedInput parsedInput) throws StorageOperationException {
        ViewAllCommand command = new ViewAllCommand(parsedInput.getTargetVisibleIndex());
        CommandResult result = command.execute(addressBook, lastShownList);
        storage.save(addressBook);
        return result;
    }

    private CommandResult executeIncorrectCommand(ParsedInput parsedInput) throws StorageOperationException {
        IncorrectCommand command = new IncorrectCommand(parsedInput.getFeedbackToUser());
        CommandResult result = command.execute();
        storage.save(addressBook);
        return result;
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
