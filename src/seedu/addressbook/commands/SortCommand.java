package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    //To change
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a sorted list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


   public CommandResult execute() throws UniquePersonList.DuplicatePersonException {

        UniquePersonList allPersons = addressBook.getAllPersons();
        List<Person> everyPerson = allPersons.getInternalList();

        //Sorts the addressbook contacts according to name alphabetically
        Collections.sort(everyPerson, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return (o1.getName().toString()).compareTo(o2.getName().toString());
            }

        });
        //Create a new UniquePersonList from the List<Person> collection
        //Then convert the UniquePersonList into List<ReadOnlyPerson>
        UniquePersonList newPersons = new UniquePersonList(everyPerson);
        List<ReadOnlyPerson> sortedPersons = newPersons.immutableListView();

        return new CommandResult(getMessageForPersonSortedListShownSummary(sortedPersons), sortedPersons);
    }


}


