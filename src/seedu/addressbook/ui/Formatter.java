package seedu.addressbook.ui;

public class Formatter {
    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    protected static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    protected static final String LS = System.lineSeparator();

    protected static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    protected static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";


    /** Offset required to convert between 1-indexing and 0-indexing.  */
    protected static final int DISPLAYED_INDEX_OFFSET = 1;

    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    protected static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    
    public static int getDisplayedIndexOffset() {
        return DISPLAYED_INDEX_OFFSET;
    }
}
