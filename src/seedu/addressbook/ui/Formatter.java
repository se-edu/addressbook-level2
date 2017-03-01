package seedu.addressbook.ui;

import java.util.List;

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
    
    public String getLINE_PREFIX(){
    	return LINE_PREFIX;
    }
    
    public String getLS(){
    	return LS;
    }
    
    public String getDIVIDER(){
    	return DIVIDER;
    }
    
    public String getMESSAGE_INDEXED_LIST_ITEM(){
    	return MESSAGE_INDEXED_LIST_ITEM;
    }
    
    public int getDISPLAYED_INDEX_OFFSET(){
    	return DISPLAYED_INDEX_OFFSET;
    }
    
    public String getCOMMENT_LINE_FORMAT_REGEX(){
    	return COMMENT_LINE_FORMAT_REGEX;
    }

    /** Formats a list of strings as a viewable indexed list. */
    protected static String getIndexedListForViewing(List<String> listItems) {
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
    private static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }   
}