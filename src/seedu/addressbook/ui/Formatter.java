package seedu.addressbook.ui;

/**
 * According to LO-SRP
 * Extract the responsibility of formatting text for display
 * So this class is essentially just doing some of the TextUI's job
 */

import static seedu.addressbook.common.Messages.MESSAGE_GOODBYE;
import static seedu.addressbook.common.Messages.MESSAGE_INIT_FAILED;
import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_USING_STORAGE_FILE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;

import java.util.List;

public class Formatter {
    //Ignore all input and output streams

    private static final String ENTER_COMMAND = "Enter command: ";
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

    /**
     * Creates the base of the message (extracted from TextUI)
     *
     * @return generatedBaseMessage as String
     */
    public String generateBaseMessage(String... message) {
        String generatedBaseMessage = "";
        for (String m : message) {
            generatedBaseMessage += LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX) + "\n";
        }
        return generatedBaseMessage;
    }

    /**
     * Generates a welcome message for TextUI
     * This method is extracted from the TextUI class
     *
     * @return The whole of welcome message
     */
    public String generateWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        return generateBaseMessage(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo,
                DIVIDER);
    }

    /**
     * Generates a goodbye message for TextUI
     * This method is extracted from the TextUI class
     *
     * @return the whole of goodbye message
     */
    public String generateGoodbyeMessage() {
        return generateBaseMessage(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    /**
     * Generates the init fail message for TextUI
     * This method is extracted from the TextUI class
     *
     * @return the whole of init fail message
     */
    public String generateInitFailMessage() {
       return generateBaseMessage(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);
    }

    /**
     * Generates the decorative text for input command text for TextUI
     * This method is extracted from TextUI class
     *
     * @return Enter command message
     */
    public String generateEnterCommandMessage() {
        return LINE_PREFIX + ENTER_COMMAND;
    }

    /**
     * Generates the result message for TextUI
     * This method is extracted from the TextUI class
     *
     * @return the whole of result message
     */
    public String generateResultMessage(String feedbackToUser) {
        return generateBaseMessage(feedbackToUser, DIVIDER);
    }

    /** Formats a list of strings as a viewable indexed list. */
    public static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString() + "\n";
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
