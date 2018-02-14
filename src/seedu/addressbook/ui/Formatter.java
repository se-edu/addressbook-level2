package seedu.addressbook.ui;


import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_USING_STORAGE_FILE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;


import java.util.List;


/**
 * Formatter class that handles all the message from textUI and format it
 */
public class Formatter {
    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";


    /** Formats a list of strings as a viewable indexed list. */
    public static String getFormattedIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getFormattedIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }


    /**
     *
     * @param version
     * @param storageFilePath
     * @return
     */
    public String formatWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        return formatShowToUser(DIVIDER, DIVIDER, MESSAGE_WELCOME, version, MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                    storageFileInfo, DIVIDER);
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    public boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /** Format Shows message(s) to the user */
    public String formatShowToUser(String... message) {
        String returnMessage = "";
        for (String m : message) {
            returnMessage += LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX) + LS;
        }
        return returnMessage;
    }

    /** Format Message to the user with two divier at the end */
    public String formatMessage(String message) {
        return formatShowToUser(message, DIVIDER, DIVIDER);
    }

    /**
     * Format Message that concat a line prefix only
     * @param message
     * @return
     */
    public String formatMessageConcatLinePrefix(String message) {
        return LINE_PREFIX + message;
    }

    /**
     * append divider and send to formatshowtouser to process for new lines
     * @param message
     * @return
     */
    public String formatMessageAppendDivider(String message) {
        return formatShowToUser(message, DIVIDER);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    public static String getFormattedIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
