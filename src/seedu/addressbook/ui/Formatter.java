package seedu.addressbook.ui;


import java.util.List;
import java.util.StringJoiner;

import static seedu.addressbook.common.Messages.MESSAGE_GOODBYE;
import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;

public class Formatter {
    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    public static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    public static final String LS = System.lineSeparator();

    public static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    public static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    public String getWelcomeMessage(String version, String storageFileInfo) {
        return format(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo,
                DIVIDER);
    }

    private String format(String... lines){
        StringJoiner joiner = new StringJoiner(LS);
        for (String l : lines) {
            joiner.add(LINE_PREFIX + l.replace("\n", LS + LINE_PREFIX));
        }
        return joiner.toString();
    }

    String getPrompt() {
        return LINE_PREFIX + "Enter command: ";
    }

    String getEcho(String fullInputLine) {
        return "[Command entered:" + fullInputLine + "]";
    }

    public String getGoodByeMessage() {
        return format(MESSAGE_GOODBYE, DIVIDER, DIVIDER);
    }

    public String formatResult(String feedbackToUser) {
        return format(feedbackToUser, Formatter.DIVIDER);
    }

    /** Formats a list of strings as a viewable indexed list. */
    public String formatListForViewing(List<String> listItems) {
        StringJoiner joiner = new StringJoiner(LS);
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            joiner.add(format(getIndexedListItem(displayIndex, listItem)));
            displayIndex++;
        }
        return joiner.toString() + LS + LINE_PREFIX;
    }

    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     */
    private String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
