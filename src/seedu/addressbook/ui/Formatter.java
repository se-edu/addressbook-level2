package seedu.addressbook.ui;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

public class Formatter {
	/** A decorative prefix added to the beginning of lines printed by AddressBook */
	private static final String LINE_PREFIX = "|| ";
	
	/** A platform independent line separator. */
	private static final String LINE_SEPARATOR = System.lineSeparator();
	private static final String DIVIDER = "===================================================";

	/** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";
    
    /** Format of a comment input line. Comment lines are silently consumed when reading user input. */
    private static final String COMMENT_LINE_FORMAT_REGEX = "#.*";
    
    /** Offset required to convert between 1-indexing and 0-indexing.  */
    public static final int DISPLAYED_INDEX_OFFSET = 1;
	
	public static String getLinePrefix() {
		return LINE_PREFIX;
	}
	
	public static String getLineSeparator() {
		return LINE_SEPARATOR;
	}
	
	public static String getDivider() {
		return DIVIDER;
	}
	
	/**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    public static boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }
	
	/** Format message(s) to the user */
    public static String formatMessageToUser(String... message) {
    	StringBuilder messageBuilder = new StringBuilder();
        for (String m : message) {
            messageBuilder.append(LINE_PREFIX + m.replace("\n", LINE_SEPARATOR + LINE_PREFIX));
            messageBuilder.append(LINE_SEPARATOR);
        }
        return messageBuilder.toString();
    }
    
    /**
     * Formats a string as a viewable indexed list item.
     *
     * @param visibleIndex visible index for this listing
     * @return formatted string
     */
    public static String getIndexedListItem(int visibleIndex, String listItem) {
        return String.format(MESSAGE_INDEXED_LIST_ITEM, visibleIndex, listItem);
    }
    
    /** Formats a list of strings as a viewable indexed list. */
    public static String getIndexedListForViewing(List<String> listItems) {
        final StringBuilder formatted = new StringBuilder();
        int displayIndex = 0 + DISPLAYED_INDEX_OFFSET;
        for (String listItem : listItems) {
            formatted.append(Formatter.getIndexedListItem(displayIndex, listItem)).append("\n");
            displayIndex++;
        }
        return formatted.toString();
    }
    
    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
    public static List<String> showPersonListView(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }
        return formattedPersons;
    }
    
}