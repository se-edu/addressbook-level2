package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.Comparator;
import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book by ascending or descending order.\n"
            + "Default: asc\n"
            + "Parameters: asc | desc\n"
            + "Example: " + COMMAND_WORD + " asc";

    public enum TYPE {
        ASC,
        DESC
    }

    private final TYPE sortType;

    public SortCommand(TYPE sortType) {
        this.sortType = sortType;
    }

    @Override
    public CommandResult execute() {
        List<Person> allPersons = addressBook.getAllPersons().mutableListView();
        if (addressBook.getAllPersons().immutableListView().size() < 1) {
            return new CommandResult(Messages.MESSAGE_INVALID_SORT_SIZE);
        } else {
            if (sortType == TYPE.ASC) {
                allPersons.sort(new Comparator<ReadOnlyPerson>() {
                    @Override
                    public int compare(ReadOnlyPerson p1, ReadOnlyPerson p2) {
                        return p1.getName().toString().compareTo(p2.getName().toString());
                    }
                });
            }
            if (sortType == TYPE.DESC) {
                allPersons.sort(new Comparator<ReadOnlyPerson>() {
                    @Override
                    public int compare(ReadOnlyPerson p1, ReadOnlyPerson p2) {
                        return p2.getName().toString().compareTo(p1.getName().toString());
                    }
                });
            }
        }

        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
