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

    private final TextUi ui;
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
        ui.showWelcomeMessage(VERSION, storage.getPath());
        List<? extends ReadOnlyPerson> lastShownList = null;
        while (true) {
            String userCommand = ui.getUserCommand();
            CommandResult result = executeCommand(userCommand, lastShownList);
            ui.showResultToUser(result.getFeedbackToUser());
            if(result.getRelevantPersons() != null) {
                lastShownList = result.getRelevantPersons();
            }
        }
    }

    /**
     * Processes user input into desired command, then executes and returns feedback.
     * 
     * @param userInputString raw input from user
     * @return feedback about how the command was executed
     */
    private CommandResult executeCommand(String userInputString, List<? extends ReadOnlyPerson> lastShownList)  {
        Command command;
        try {
            command = new Parser().parseCommand(userInputString);
        } catch (Parser.ParseException pe) {
            return new CommandResult(pe.getMessage());
        }

        command.injectDependencies(ui, addressBook, lastShownList);
        CommandResult result = command.execute();
        saveChangesToStorageFile();
        return result;
    }

    /**
     * Saves any changes to the storage file. Exits program if an error is encountered.
     */
    private void saveChangesToStorageFile() {
        try {
            storage.save(addressBook);
        } catch (StorageOperationException e) {
            ui.showToUser(e.getMessage());
            throw new RuntimeException(e);
        }
    }


}