package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a sorted list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        ArrayList<Person> allPersons = new ArrayList<>(addressBook.getAllPersons().mutableListView());
        Collections.sort(allPersons, (Person p1, Person p2) -> p1.getName().toString().compareTo(p2.getName().toString()));
                //Comparator.comparing(Person::getName));
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}