package seedu.addressbook.commands;

import seedu.addressbook.data.person.*;

import java.util.List;


public class SortCommand extends Command{
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address book by the name of persons.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableSortedListView();
        return new CommandResult(getMessageForPersonSortedListShownSummary(allPersons), allPersons);
    }
}
