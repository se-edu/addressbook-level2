package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SORT_ACKNOWEDGEMENT = "Sorting Address Book ...";

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.sortedAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
