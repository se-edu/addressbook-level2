package seedu.addressbook;

import static seedu.addressbook.TextUi.*;
import static seedu.addressbook.storage.StorageFile.*;

import seedu.addressbook.commands.*;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.storage.StorageFile;

import java.io.*;


/**
 * Entry point of the address book application.
 * Initializes the application and starts the interaction with the user.
 */
public class Main {

    /**
     * Version info of the program.
     */
    public static final String VERSION = "AddessBook Level 2 - Version 1.0";

    private final TextUi ui;
    private final Parser parser;
    private final StorageFile storage;
    private final AddressBook addressBook;

    /**
     * Sets up the different components and loads up the model from the storage file.
     *
     * @param launchArgs arguments supplied by the user at program launch
     * @param inputStream user text input source
     * @param outputStream user text output acceptor
     *
     */
    public Main(String[] launchArgs, InputStream inputStream, PrintStream outputStream){
        this.ui = new TextUi(inputStream, outputStream);
        this.parser = new Parser();
        try {
            this.storage = createStorageFile(launchArgs);
            this.addressBook = storage.load();
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

    public static void main(String... launchArgs) {
        new Main(launchArgs, System.in, System.out).start();
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

    /**
     * Starts program execution.
     * Assumption: All required objects have been initialised.
     * TODO: Eliminate above assumption (move initialization logic to start method?)
     */
    public void start() {
        ui.showWelcomeMessage(VERSION);
        ui.showToUser(String.format(MESSAGE_USING_STORAGE_FILE, storage.toString()));
        while (true) {
            String userCommand = ui.getUserCommand();
            String feedback = executeCommand(userCommand);
            ui.showResultToUser(feedback);
        }
    }

    /**
     * Displays the goodbye message and exits the runtime.
     */
    private void exitProgram() {
        Command exit = new ExitCommand();
        exit.injectDependencies(ui, addressBook);
        exit.execute();
    }

    /**
     * Processes user input into desired command, then executes and returns feedback.
     * 
     * @param userInputString raw input from user
     * @return feedback about how the command was executed
     */
    private String executeCommand(String userInputString) {
        Command command;
        try {
            command = parser.parseCommand(userInputString);
        } catch (Parser.ParseException pe) {
            return pe.getMessage();
        }

        command.injectDependencies(ui, addressBook);
        String result = command.execute();
        saveChangesToStorageFile();
        return result;
    }

    /**
     * Saves any changes to the storage file. Exits program if an error is encountered.
     */
    private void saveChangesToStorageFile() {
        try {
            storage.save(addressBook);
        } catch (StorageOperationException soe) {
            ui.showToUser(soe.getMessage());
            exitProgram();
        }
    }


}