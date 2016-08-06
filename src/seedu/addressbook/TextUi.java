package seedu.addressbook;

import seedu.addressbook.model.person.ReadOnlyPerson;

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
    public static final String MESSAGE_ADDED = "New person added: %1$s, Phone: %2$s, Email: %3$s";
    public static final String MESSAGE_ADDRESSBOOK_CLEARED = "Address book has been cleared!";
    public static final String MESSAGE_COMMAND_HELP = "%1$s: %2$s";
    public static final String MESSAGE_COMMAND_HELP_PARAMETERS = "\tParameters: %1$s";
    public static final String MESSAGE_COMMAND_HELP_EXAMPLE = "\tExample: %1$s";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DISPLAY_PERSON_DATA = "%1$s  Phone Number: %2$s  Email: %3$s";
    public static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    public static final String MESSAGE_GOODBYE = "Exiting Address Book... Good bye!";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format: %1$s " + LS + "%2$s";
    public static final String MESSAGE_INVALID_FILE = "The given file name [%1$s] is not a valid file name!";
    public static final String MESSAGE_INVALID_PROGRAM_ARGS = "Too many parameters! Correct program argument format:"
            + LS + "\tjava AddressBook"
            + LS + "\tjava AddressBook [custom storage file path]";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_STORAGE_FILE_CONTENT = "Storage file has invalid content";
    public static final String MESSAGE_PERSON_NOT_IN_ADDRESSBOOK = "Person could not be found in address book";
    public static final String MESSAGE_ERROR_CREATING_STORAGE_FILE = "Error: unable to create file: %1$s";
    public static final String MESSAGE_ERROR_MISSING_STORAGE_FILE = "Storage file missing: %1$s";
    public static final String MESSAGE_ERROR_READING_FROM_FILE = "Unexpected error: unable to read from file: %1$s";
    public static final String MESSAGE_ERROR_WRITING_TO_FILE = "Unexpected error: unable to write to file: %1$s";
    public static final String MESSAGE_PERSONS_FOUND_OVERVIEW = "%1$d persons found!";
    public static final String MESSAGE_STORAGE_FILE_CREATED = "Created new empty storage file: %1$s";
    public static final String MESSAGE_WELCOME = "Welcome to your Address Book!";
    public static final String MESSAGE_USING_DEFAULT_FILE = "Using default storage file : " + DEFAULT_STORAGE_FILEPATH;

    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_ADD_DESC = "Adds a person to the address book.";
    public static final String COMMAND_ADD_PARAMETERS = "NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...";
    public static final String COMMAND_ADD_EXAMPLE = COMMAND_WORD_ADD + 
            " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owes_money";

    public static final String COMMAND_WORD_FIND = "find";
    public static final String COMMAND_FIND_DESC = "Finds all persons whose names contain any of the specified "
            + "keywords (case-sensitive) and displays them as a list with index numbers.";
    public static final String COMMAND_FIND_PARAMETERS = "KEYWORD [MORE_KEYWORDS]...";
    public static final String COMMAND_FIND_EXAMPLE = COMMAND_WORD_FIND + " alice bob charlie";

    public static final String COMMAND_WORD_LIST = "list";
    public static final String COMMAND_LIST_DESC = "Displays all persons as a list with index numbers.";
    public static final String COMMAND_LIST_EXAMPLE = COMMAND_WORD_LIST;

    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_DELETE_DESC = "Deletes a person identified by the index number used in "
            + "the last find/list call.";
    public static final String COMMAND_DELETE_PARAMETER = "INDEX";
    public static final String COMMAND_DELETE_EXAMPLE = COMMAND_WORD_DELETE + " 1";

    public static final String COMMAND_WORD_CLEAR = "clear";
    public static final String COMMAND_CLEAR_DESC = "Clears address book permanently.";
    public static final String COMMAND_CLEAR_EXAMPLE = COMMAND_WORD_CLEAR;

    public static final String COMMAND_WORD_HELP = "help";
    public static final String COMMAND_HELP_DESC = "Shows program usage instructions.";
    public static final String COMMAND_HELP_EXAMPLE = COMMAND_WORD_HELP;

    public static final String COMMAND_WORD_EXIT = "exit";
    public static final String COMMAND_EXIT_DESC = "Exits the program.";
    public static final String COMMAND_EXIT_EXAMPLE = COMMAND_WORD_EXIT;

    /**
     * Offset required to convert between 1-indexing and 0-indexing.COMMAND_
     */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    private final Scanner in;
    private final PrintStream out;
    private final Parser parser;
    
    // preserves the last showed listing of persons for understanding user person references
    // based on the the last listing they saw
    private List<? extends ReadOnlyPerson> lastShownPersonListing = new ArrayList<>();
    // latest input line retrieved from getUserInput
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
    public String getUserInput() {
        out.print(LINE_PREFIX + "Enter command: ");
        String fullInputLine = in.nextLine();
        // silently consume all ignored lines
        while (parser.shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }
        return fullInputLine;
    }

    /*
     * ==============NOTE TO STUDENTS======================================
     * The method header comment can be omitted if the method is trivial
     * and the header comment is going to be almost identical to the method
     * signature anyway.
     * ====================================================================
     */
    public void showWelcomeMessage(String version) {
        showToUser(DIVIDER, DIVIDER, version, MESSAGE_WELCOME, DIVIDER);
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
    public void echoUserCommand(String userCommand) {
        showToUser("[Command entered:" + userCommand + "]");
    }

   /* ==============NOTE TO STUDENTS======================================
    * Note how the method below uses Java 'Varargs' feature so that the
    * method can accept a varying number of message parameters.
    * ====================================================================
    */
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
     * @return the person in the last viewed l
     */
    public ReadOnlyPerson getPersonFromLastShownListing(int displayedIndex) {
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
        lastShownPersonListing = persons; // update previous view
    }

    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     * All contact details are shown.
     */
    public void showFullPersonListView(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextShowAll());
        }
        showToUserAsIndexedList(formattedPersons);
        lastShownPersonListing = persons; // update previous view
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



}
