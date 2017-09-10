package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.ArrayList;


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
        ArrayList<Person> allPersons = addressBook.getAllPersons().mutableListView();
        //Collections.sort(allPersons);
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}