package seedu.addressbook.ui;


import java.util.List;
import java.util.StringJoiner;

import static seedu.addressbook.common.Messages.MESSAGE_INIT_FAILED;


public class Formatter {
    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    public String formatWelcomeMessage(String welcome, String version, String usage, String storageFileInfo) {
        return format(
                DIVIDER,
                DIVIDER,
                welcome,
                version,
                usage,
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

    public String formatPrompt() {
        return LINE_PREFIX + "Enter command: ";
    }

    public String formatEcho(String fullInputLine) {
        return "[Command entered:" + fullInputLine + "]";
    }

    public String formatGoodByeMessage(String goodbyeMessage) {
        return format(goodbyeMessage, DIVIDER, DIVIDER);
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

    public String formatInitFailedMessage(String messageInitFailed) {
        return format(messageInitFailed, DIVIDER, DIVIDER);
    }
}
