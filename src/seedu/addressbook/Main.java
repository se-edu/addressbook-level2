package seedu.addressbook;

import static seedu.addressbook.TextUi.*;
import static seedu.addressbook.storage.StorageFile.*;

import seedu.addressbook.commands.*;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.storage.StorageFile;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* ==============NOTE TO STUDENTS======================================
 * This class header comment below is brief because details of how to
 * use this class are documented elsewhere.
 * ====================================================================
 */

/**
 * Entry point of the address book application.
 * Contains command execution logic; initialises and uses the different components.
 */
public class Main {

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
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Signals that the main application had a problem while initialising.
     */
    public static class MainInitialisationException extends Exception {}

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
        this.ui = new TextUi(inputStream, outputStream);

        try {
            this.storageFile = new StorageFile(storageFilePath);
            this.addressBook = storageFile.loadAddressBookFromFile();
        } catch (InvalidStorageFilePathException | StorageOperationException e) {
            ui.showToUser(e.getMessage());
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
            main.start();
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
        new ExitCommand(ui).execute();
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
    public void start() {
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

    /**
     * Processes user input into desired command, then executes and returns feedback.
     * 
     * @param userInputString raw input from user
     * @return feedback about how the command was executed
     */
    private String executeCommand(String userInputString) {
        if (!isInputCommandValid(userInputString)) {
            return String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE);
        }
        final Command command = identifyAndPrepareCommand(userInputString);
        final String result = command.execute();
        saveChangesToStorageFile();
        return result;
    }

    /**
     * Checks if a user input command line fulfills the most basic format separating the
     * command word and the command arguments.
     */
    public static boolean isInputCommandValid(String userInputString) {
        return userInputString.trim().matches(BASIC_COMMAND_FORMAT.pattern());
    }

    /**
     * Parses raw user input and prepares a new instance of the correct command object type.
     *
     * @param userInputString raw input from user
     * @return the corresponding command object for execution
     */
    private Command identifyAndPrepareCommand(String userInputString) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInputString.trim());
        matcher.matches();
        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

            case AddPersonCommand.COMMAND_WORD:
                return new AddPersonCommand(arguments, addressBook);

            case DeletePersonCommand.COMMAND_WORD:
                return new DeletePersonCommand(arguments, addressBook, ui);

            case ClearAddressBookCommand.COMMAND_WORD:
                return new ClearAddressBookCommand(addressBook);

            case FindPersonsByWordsInNameCommand.COMMAND_WORD:
                return new FindPersonsByWordsInNameCommand(arguments, addressBook, ui);

            case ListAllPersonsCommand.COMMAND_WORD:
                return new ListAllPersonsCommand(addressBook, ui);

            case ViewPersonDetailsCommand.COMMAND_WORD:
                return new ViewPersonDetailsCommand(arguments, addressBook, ui);

            case ViewAllPersonDetailsCommand.COMMAND_WORD:
                return new ViewAllPersonDetailsCommand(arguments, addressBook, ui);

            case ExitCommand.COMMAND_WORD:
                return new ExitCommand(ui);

            case HelpCommand.COMMAND_WORD: // Fallthrough
            default:
                return new HelpCommand();
        }
    }

    /**
     * Saves any changes to the storage file. Exits program if an error is encountered.
     */
    private void saveChangesToStorageFile() {
        try {
            storageFile.saveAddressBookToFile(addressBook);
        } catch (StorageOperationException soe) {
            ui.showToUser(soe.getMessage());
            exitProgram();
        }
    }


}