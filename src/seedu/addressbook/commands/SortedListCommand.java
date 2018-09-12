package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;


/**
 * Lists all persons in the address book to the user in ascending alphabetical order.
 */
public class SortedListCommand extends Command {

    public static final String COMMAND_WORD = "sortedlist";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list in ascending alphabetical order.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> sortedAllPersons = addressBook.getSortedAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(sortedAllPersons), sortedAllPersons);
    }

}
