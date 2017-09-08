package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

/**
 * Lists all persons in the address book to the user in alphabetical order.
 */

public class SortedListCommand extends Command {

    public static final String COMMAND_WORD = "sortedlist";
    public static final String MESSAGE_USAGE =
            "Displays all persons in the address book as a list with index numbers in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS = "Address book has been sorted!";

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableSortedListView();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
