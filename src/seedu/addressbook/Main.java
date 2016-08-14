package seedu.addressbook;

import seedu.addressbook.model.person.ReadOnlyPerson;
import seedu.addressbook.storage.StorageFile.*;

import seedu.addressbook.commands.*;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;

import java.io.*;
import java.util.List;


/**
 * Entry point of the address book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    /**
     * Version info of the program.
     */
    public static final String VERSION = "AddessBook Level 2 - Version 1.0";

    private TextUi ui;
    private StorageFile storage;
    private AddressBook addressBook;

    /**
     * The list of person shown to the user most recently.
     */
    private List<? extends ReadOnlyPerson> lastShownList = null;


    public static void main(String... launchArgs) {
        new Main().run(launchArgs, System.in, System.out);
    }

    /**
     * Runs the program until termination.
     */
    public void run(String[] launchArgs, InputStream inputStream, PrintStream outputStream) {
        start(launchArgs, inputStream, outputStream);
        runCommandLoopUntilExitCommand();
        exit();
    }

    /**
     * Sets up the different components, loads up the data from the storage file, and prints the welcome message.
     *
     * @param launchArgs arguments supplied by the user at program launch
     * @param inputStream user text input source
     * @param outputStream user text output acceptor
     *
     */
    private void start(String[] launchArgs, InputStream inputStream, PrintStream outputStream) {
        try {
            this.ui = new TextUi(inputStream, outputStream);
            this.storage = createStorageFile(launchArgs);
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

    /**
     * Prints the Goodbye message and exits.
     */
    private void exit() {
        ui.showGoodbyeMessage();
        System.exit(0);
    }

    /**
     * Reads the user command and executes it, until the user issues the exit command.
     */
    private void runCommandLoopUntilExitCommand() {
        Command command;
        do {
            String userCommand = ui.getUserCommand();
            command = new Parser().parseCommand(userCommand);
            CommandResult result = executeCommand(command);
            updateLastShownList(result);
            ui.showResultToUser(result);

        } while(!ExitCommand.isExit(command));
    }

    /**
     * Updates the {@link #lastShownList} if the result contains a list of Persons
     */
    private void updateLastShownList(CommandResult result) {
        if(result.getRelevantPersons() != null) {
            lastShownList = result.getRelevantPersons();
        }
    }

    /**
     * Processes user input into desired command, then executes and returns feedback.
     * 
     * @param command user command
     * @return feedback about how the command was executed
     */
    private CommandResult executeCommand(Command command)  {
        try {
            command.setData(addressBook, lastShownList);
            CommandResult result = command.execute();
            storage.save(addressBook);
            return result;
        } catch (Exception e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    /**
     * Creates the StorageFile object based on the user specified path (if any) or the default storage path.
     * @param launchArgs arguments supplied by the user at program launch
     * @throws InvalidStorageFilePathException if the target file path is incorrect.
     */
    private StorageFile createStorageFile(String[] launchArgs) throws InvalidStorageFilePathException {
        boolean isStorageFileSpecifiedByUser = launchArgs.length > 0;
        return isStorageFileSpecifiedByUser ? new StorageFile(launchArgs[0]) : new StorageFile();
    }



}