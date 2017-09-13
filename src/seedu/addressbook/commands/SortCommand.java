package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the contact list in alphabetical order\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> sortedList = sortByAlphabeticalOrder(addressBook.getAllPersons().immutableListView());
        return new CommandResult(getMessageForPersonListShownSummary(sortedList), sortedList);
    }

    private List<ReadOnlyPerson> sortByAlphabeticalOrder(List<ReadOnlyPerson> contactList) {
        NameComparator nameComparator = new NameComparator();
        List<ReadOnlyPerson> sortedList = new ArrayList<>();
        for (ReadOnlyPerson person: addressBook.getAllPersons()) {
            sortedList.add(person);

        }
        Collections.sort(sortedList, nameComparator);

        return sortedList;
    }
}

class NameComparator implements Comparator<ReadOnlyPerson> {
    public int compare(ReadOnlyPerson person1, ReadOnlyPerson person2) {
        return person1.getName().fullName.toLowerCase().compareTo(person2.getName().fullName.toLowerCase());
    }
    public boolean equals(Object obj) {
        return this == obj;
    }

}
