package seedu.addressbook.ui;

import java.util.List;

public class Formatter {
    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    /** A decorative divider to divide lines of output*/
    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    public String formatFragments(String... message) {
        String stringChain = "";
        for (String m : message) {
            stringChain += (LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX) + LS);
        }
        return stringChain;
    }

    public String formatInitFailed(String message) {
        return formatFragments(message, DIVIDER, DIVIDER);
    }

    public String formatWelcomeMessage(String welcomeMessage,
                                       String version,
                                       String storageFileInfo,
                                       String launchArgumentMessage) {
        return formatFragments(
                DIVIDER,
                DIVIDER,
                welcomeMessage,
                version,
                launchArgumentMessage,
                storageFileInfo,
                DIVIDER);
    }

    public String formatEnterCommandPrompt(String enterCommandPrompt) {
        return LINE_PREFIX + enterCommandPrompt;
    }

    public String formatEnterCommandResponse(String enteredCommandResponse){
        return enteredCommandResponse + "[Command entered:" + enteredCommandResponse + "]" + LS;;
    }

    public String formatResult(String feedbackToUser) {
        return formatFragments(feedbackToUser, DIVIDER);
    }

    public String formatGoodbyeMessage(String message) {
        return formatFragments(message, DIVIDER, DIVIDER);
    }

    /** Formats a list of strings as a viewable indexed list. */
    public static String formatIndexList(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + TextUi.DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(getIndexedListItem(displayIndex, listItem)).append("\n");
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
}
