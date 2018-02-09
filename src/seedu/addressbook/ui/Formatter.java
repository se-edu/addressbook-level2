package seedu.addressbook.ui;

import java.util.List;

import static seedu.addressbook.common.Messages.*;

public class Formatter {

    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    public static final String LINE_PREFIX = "|| ";


    public static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;


    /**
     * Creates and formats the welcome message and print result to user
     * @param version
     * @param storageFilePath
     * @param textui
     */


    public void createWelcomeMessage(String version, String storageFilePath, TextUi textui){
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        textui.showToUser(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo,
                DIVIDER);
    }

    /**
     * Creates and formats the goodbye message and print result to user
     * @param textui
     */

    public void createGoodbyeMessage(TextUi textui){

        textui.showToUser(MESSAGE_GOODBYE, DIVIDER, DIVIDER);

    }

    /**
     * Creates and formats the initialise fail message and print result to user
     * @param textui
     */

    public void createInitFailMessage(TextUi textui){

        textui.showToUser(MESSAGE_INIT_FAILED, DIVIDER, DIVIDER);

    }



    /** Formats a list of strings as a viewable indexed list. */
    public String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
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
    private String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
}
