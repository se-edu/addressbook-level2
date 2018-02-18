package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.List;


/**
 * List all persons in the address book in a sorted order to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list in sorted order with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<Person> allPersons = addressBook.getAllPersons().getInternalList();
        allPersons.sort((Person p1, Person p2)-> p1.getName().toString().compareTo(p2.getName().toString()));
        return new CommandResult(getMessageForPersonListShownSummary(allPersons),
                allPersons);
    }
}
