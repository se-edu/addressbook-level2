package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;

import java.util.List;


/**
 * Sorts and lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts and displays all persons in the address book as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * TODO
     *
     * @return
     */
    @Override
    public CommandResult execute() {
        List<Person> allPersons = addressBook.getAllPersons().listView();
        allPersons.sort((x1, x2) -> x1.getName().getFirstLetter() - x2.getName().getFirstLetter());
        addressBook.reconstructList(allPersons);
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}