package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book alphabetically as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        List<ReadOnlyPerson> mutablePersons = new ArrayList<>(allPersons);
        Collections.sort(mutablePersons, new SortByName());
        return new CommandResult(getMessageForPersonListShownSummary(mutablePersons), mutablePersons);
    }
}

class SortByName implements Comparator<ReadOnlyPerson> {
    public int compare(ReadOnlyPerson p1, ReadOnlyPerson p2) {
        if (p1 == null && p2 == null) return 0;
        if (p2 == null) return 1;
        if (p1 == null) return -1;

        return p1.getName().fullName.toUpperCase().compareTo(p2.getName().fullName.toUpperCase());
    }
}
