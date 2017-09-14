package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.List;

/**
 * Sorts the address book in alphabetical order based on names.
 */
public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the address book. "
            + "Parameters: ascending/descending \n"
            + "Example: " + COMMAND_WORD
            + " ascending";

    public static final String MESSAGE_SUCCESS = "Address Book sorted";
    public static final String MESSAGE_WRONG_SORT = "No such sort";

    private final String toSort;

    public SortCommand(String sorting) {
        this.toSort = sorting;
    }

    @Override
    public CommandResult execute(){
        try {
            addressBook.sortPerson(toSort);
            List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
            return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
        }
        catch (UniquePersonList.WrongSortException dpe) {
            return new CommandResult(MESSAGE_WRONG_SORT);
        }
    }
}
