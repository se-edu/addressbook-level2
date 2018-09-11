package seedu.addressbook.commands;

import seedu.addressbook.data.person.UniquePersonList;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the current list of people by a property. "
            + "Parameters: PROPERTY ORDER\n"
            + "Example: " + COMMAND_WORD
            + " name -1";

    public static final String MESSAGE_SUCCESS = "List sorted by: $s in %s order";

    private String propertyName;

    private boolean ascending;

    public SortCommand(String propertyName, String ascending) {
        this.propertyName = propertyName;
        this.ascending = isSortAscending(ascending);
    }

    @Override
    public CommandResult execute() {
        // TO-DO: Add implementation (in a try-catch if required)
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                propertyName,
                (ascending) ? "ascending" : "descending"));
    }

    /**
     * Determines if orderString is ascending or not.
     * @param orderString
     * @return true if orderString is positive.
     */
    private static boolean isSortAscending(String orderString) {
        boolean ascending = true;
        if (isInteger(orderString)) {
            int orderParam = Integer.parseInt(orderString);
            ascending = (orderParam < 0) ? false : true;
        }
        return ascending;
    }

    //TO-DO: Move to Utils
    /**
     * Checks if a given string is a valid integer.
     * @param strNum
     * @return true if given string can be parsed as an integer.
     */
    public static boolean isInteger(String strNum) {
        try {
            Integer num = Integer.parseInt(strNum);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

}
