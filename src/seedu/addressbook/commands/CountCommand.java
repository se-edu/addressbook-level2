package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

/**
 * Count all persons in the address book and display the result.
 */
public class CountCommand extends Command{

    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Count all persons in the address book and display the result.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons)/*, allPersons*/);
    }
}



