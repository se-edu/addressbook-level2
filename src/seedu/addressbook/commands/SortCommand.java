package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;
import java.util.List;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list in alphabetical order.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allSortedPersons = addressBook.getAllPersons().sortedListView();
        return new CommandResult(getMessageForPersonListShownSummary(allSortedPersons), allSortedPersons);
    }
}