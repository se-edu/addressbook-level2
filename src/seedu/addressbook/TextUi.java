package seedu.addressbook;

import seedu.addressbook.model.person.ReadOnlyPerson;
import seedu.addressbook.Parser.Command;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Encapsulates the textual user interface (both input and output).
 * Should not be too tightly couple
 */
public class TextUi {

    /**
     * A decorative prefix added to the beginning of lines printed by AddressBook
     */
    public static final String LINE_PREFIX = "|| ";

    /**
     * A platform independent line separator.
     */
    public static final String LS = System.lineSeparator() + LINE_PREFIX;

    public static final String DIVIDER = "===================================================";

    /*
     * ==============NOTE TO STUDENTS======================================
     * These messages shown to the user are defined in one place for convenient
     * editing and proof reading. Such messages are considered part of the UI
     * and may be subjected to review by UI experts or technical writers. Note
     * that Some of the strings below include '%1$s' etc to mark the locations
     * at which java String.format(...) method can insert values.
     * ====================================================================
     */

    public static final String MESSAGE_ADD_PERSON_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_ADDRESSBOOK_CLEARED = "Address book has been cleared!";
    public static final String MESSAGE_CHANGES_SAVED_TO_STORAGE_FILE = "Changes saved to storage file.";
    public static final String MESSAGE_COMMAND_HELP = "%1$s: %2$s";
    public static final String MESSAGE_COMMAND_HELP_PARAMETERS = "\tParameters: %1$s";
    public static final String MESSAGE_COMMAND_HELP_EXAMPLE = "\tExample: %1$s";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";
    public static final String MESSAGE_ERROR_MISSING_STORAGE_FILE = "Storage file missing: %1$s";
    public static final String MESSAGE_ERROR_READING_FROM_FILE = "Unexpected error: unable to read from file: %1$s";
    public static final String MESSAGE_ERROR_WRITING_TO_FILE = "Unexpected error: unable to write to file: %1$s";
    public static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    public static final String MESSAGE_GOODBYE = "Exiting Address Book... Good bye!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format: %1$s " + LS + "%2$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_STORAGE_FILE_CONTENT = "Storage file has invalid content";;
    public static final String MESSAGE_INVALID_STORAGE_FILE_PATH = "Storage file should be valid and end with \".txt\"";
    public static final String MESSAGE_PERSON_NOT_IN_ADDRESSBOOK = "Person could not be found in address book";
    public static final String MESSAGE_PERSONS_FOUND_OVERVIEW = "%1$d persons found!";
    public static final String MESSAGE_WELCOME = "Welcome to your Address Book!";
    public static final String MESSAGE_USING_STORAGE_FILE = "Using storage file : %1$s";
    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Viewing person: %1$s";

    public static final String COMMAND_ADD_DESC = "Adds a person to the address book.";
    public static final String COMMAND_ADD_PARAMETERS = "NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...";
    public static final String COMMAND_ADD_EXAMPLE = Command.ADD.commandWord + 
            " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owes_money";

    public static final String COMMAND_FIND_DESC = "Finds all persons whose names contain any of the specified "
            + "keywords (case-sensitive) and displays them as a list with index numbers.";
    public static final String COMMAND_FIND_PARAMETERS = "KEYWORD [MORE_KEYWORDS]...";
    public static final String COMMAND_FIND_EXAMPLE = Command.FIND.commandWord + " alice bob charlie";

    public static final String COMMAND_LIST_DESC = "Displays all persons as a list with index numbers.";
    public static final String COMMAND_LIST_EXAMPLE = Command.LIST.commandWord;

    public static final String COMMAND_VIEW_DESC = "Views the non-private details of the person identified by the "
            + "index number shown in the last person listing.";
    public static final String COMMAND_VIEW_PARAMETERS = "INDEX";
    public static final String COMMAND_VIEW_EXAMPLE = Command.VIEW.commandWord + " 2";

    public static final String COMMAND_VIEW_ALL_DESC = "Views all details of the person identified by the index number"
            + " shown in the last person listing.";
    public static final String COMMAND_VIEW_ALL_PARAMETERS = "INDEX";
    public static final String COMMAND_VIEW_ALL_EXAMPLE = Command.VIEW_ALL.commandWord + " 3";

    public static final String COMMAND_DELETE_DESC = "Deletes the person identified by the index number used in "
            + "the last person listing.";
    public static final String COMMAND_DELETE_PARAMETERS = "INDEX";
    public static final String COMMAND_DELETE_EXAMPLE = Command.DELETE.commandWord + " 1";

    public static final String COMMAND_CLEAR_DESC = "Clears address book permanently.";
    public static final String COMMAND_CLEAR_EXAMPLE = Command.CLEAR.commandWord;

    public static final String COMMAND_HELP_DESC = "Shows program usage instructions.";
    public static final String COMMAND_HELP_EXAMPLE = Command.HELP.commandWord;

    public static final String COMMAND_EXIT_DESC = "Exits the program.";
    public static final String COMMAND_EXIT_EXAMPLE = Command.EXIT.commandWord;

    /**
     * Offset required to convert between 1-indexing and 0-indexing.COMMAND_
     */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private final Scanner in;
    private final PrintStream out;
    private final Parser parser;
    
    /**
     * preserves the last showed listing of persons for understanding user person references
     * based on the the last listing they saw
     */
    private List<? extends ReadOnlyPerson> lastShownPersonListing = new ArrayList<>();

    /**
     * latest input line retrieved from {@link #getUserCommand()}
     */
    private String lastEnteredCommand = "";
    
    public TextUi(InputStream in, PrintStream out, Parser parser) {
        this.in = new Scanner(in);
        this.out = out;
        this.parser = parser;
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     *
     * @see Parser#shouldIgnore(String)
     * @return full line entered by the user
     */
    public String getUserCommand() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();
        // silently consume all ignored lines
        while (parser.shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        lastEnteredCommand = fullInputLine;
        return fullInputLine;
    }

    /**
     * Retrieves the latest entered non-ignored input line read from the user by {@link #getUserCommand()}
     */
    public String getLastEnteredCommand() {
        return lastEnteredCommand;
    }

    public void showWelcomeMessage(String version) {
        showToUser(DIVIDER, DIVIDER, version, MESSAGE_WELCOME, DIVIDER);
    }

    /**
     * Echoes the user input back to the user.
     */
    public void echoLastEnteredUserCommand() {
        showToUser("[Command entered:" + lastEnteredCommand + "]");
    }

    /**
     * Shows message(s) to the user
     */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(LINE_PREFIX + m);
        }
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public void showResultToUser(String result) {
        showToUser(result, DIVIDER);
    }

    /**
     * Retrieves the person from the last person listing view specified by the displayed list index.
     * 
     * @param displayedIndex the index of the target person as shown to user
     * @return the person in the last viewed listing
     */
    public ReadOnlyPerson getPersonFromLastShownListing(int displayedIndex) throws IndexOutOfBoundsException {
        return lastShownPersonListing.get(displayedIndex - DISPLAYED_INDEX_OFFSET);
    }
    
    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
    public void showPersonListView(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }
        showToUserAsIndexedList(formattedPersons);
        updateLastShownPersonListing(persons);
    }

    /**
     * Updates and tracks the last viewed person listing to the user.
     */
    private void updateLastShownPersonListing(List<? extends ReadOnlyPerson> persons) {
        lastShownPersonListing = new ArrayList<>(persons); // copy to insulate from changes in original list
    }
    
    /**
     * Shows a list of strings to the user, formatted as an indexed list.
     */
    public void showToUserAsIndexedList(List<String> list) {
        showToUser(getIndexedListForViewing(list));
    }

    /**
     * Formats a list of strings as a viewable indexed list.
     */
    public static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append(LS);
            displayIndex++;
        }
        return formatted.toString();
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    public static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }

    /*
     * ===============================================================
     *          COMPLEX MESSAGE CONSTRUCTION CONVENIENCE METHODS
     * ===============================================================
     */

    /**
     * Constructs a generic feedback message for an invalid command from user, with instructions for correct usage.
     *
     * @param invalidCommand original invalid command input
     * @param correctUsageInfo message showing the correct usage
     * @return invalid command args feedback message
     */
    public static String getMessageForInvalidCommandInput(String invalidCommand, String correctUsageInfo) {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, invalidCommand, correctUsageInfo);
    }


    /*
     * ===============================================
     *         COMMAND HELP INFO FOR USERS
     * ===============================================
     */

    /**
     * @return  Usage info for all commands
     */
    public static String getUsageInfoForAllCommands() {
        return getUsageInfoForAddCommand() + LS
                + getUsageInfoForFindCommand() + LS
                + getUsageInfoForListCommand() + LS
                + getUsageInfoForViewCommand() + LS
                + getUsageInfoForViewAllCommand() + LS
                + getUsageInfoForDeleteCommand() + LS
                + getUsageInfoForClearCommand() + LS
                + getUsageInfoForExitCommand() + LS
                + getUsageInfoForHelpCommand();
    }

    public static String getUsageInfoForAddCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.ADD.commandWord, COMMAND_ADD_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_ADD_PARAMETERS) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_ADD_EXAMPLE) + LS;
    }

    public static String getUsageInfoForFindCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.FIND.commandWord, COMMAND_FIND_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_FIND_PARAMETERS) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_FIND_EXAMPLE) + LS;
    }

    public static String getUsageInfoForDeleteCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.DELETE.commandWord, COMMAND_DELETE_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DELETE_PARAMETERS) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE) + LS;
    }

    public static String getUsageInfoForClearCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.CLEAR.commandWord, COMMAND_CLEAR_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_CLEAR_EXAMPLE) + LS;
    }

    public static String getUsageInfoForListCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.LIST.commandWord, COMMAND_LIST_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_LIST_EXAMPLE) + LS;
    }

    public static String getUsageInfoForViewCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.VIEW.commandWord, COMMAND_VIEW_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_VIEW_PARAMETERS) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_VIEW_EXAMPLE) + LS;
    }

    public static String getUsageInfoForViewAllCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.VIEW_ALL.commandWord, COMMAND_VIEW_ALL_DESC) + LS
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_VIEW_ALL_PARAMETERS) + LS
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_VIEW_ALL_EXAMPLE) + LS;
    }

    public static String getUsageInfoForHelpCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.HELP.commandWord, COMMAND_HELP_DESC)
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_HELP_EXAMPLE);
    }

    public static String getUsageInfoForExitCommand() {
        return String.format(MESSAGE_COMMAND_HELP, Command.EXIT.commandWord, COMMAND_EXIT_DESC)
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EXIT_EXAMPLE);
    }

}
