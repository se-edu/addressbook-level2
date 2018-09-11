package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the current list of people by a property. "
            + "Parameters: PROPERTY ORDER\n"
            + "Example: " + COMMAND_WORD
            + " name -1";

    public static final String MESSAGE_INVALID_PROPERTY = "Unable to sort by invalid %s.";

    public static final String MESSAGE_SUCCESS = "List sorted by: %s in %s order";

    private String propertyName;

    private boolean ascending;

    public SortCommand(String propertyName, String ascending) {
        this.propertyName = propertyName.toLowerCase();
        this.ascending = isSortAscending(ascending);
    }

    @Override
    public CommandResult execute() {
        // TO-DO: Add implementation (in a try-catch if required)
        List<ReadOnlyPerson> personsList = addressBook.getAllPersons().clonedListView();
        int sign = (ascending) ? 1 : -1;
        switch (propertyName) {
            case "name":
                personsList.sort((o1, o2) -> {
                    if (o1.hasSameData(o2))
                        return 0;
                    return sign * o1.getName().fullName.compareTo(o2.getName().fullName);
                });
                break;
            case "email":
                personsList.sort((o1, o2) -> {
                    if (o1.hasSameData(o2))
                        return 0;
                    return sign * o1.getEmail().value.compareTo(o2.getEmail().value);
                });
                break;
            case "address":
                personsList.sort((o1, o2) -> {
                    if (o1.hasSameData(o2))
                        return 0;
                    return sign * o1.getAddress().value.compareTo(o2.getAddress().value);
                });
                break;
            default:
                return new CommandResult(String.format(MESSAGE_INVALID_PROPERTY, propertyName));
        }

        return new CommandResult(getMessageForPersonListShownSummary(personsList), personsList);
    }


    /**
     * Determines if orderString is ascending or not.
     * @param orderString
     * @return true if orderString is positive.
     */
    private static boolean isSortAscending(String orderString) {
        boolean ascending = true;
        int orderParam = Integer.parseInt(orderString);
        ascending = (orderParam < 0) ? false : true;
        return ascending;
    }


}
