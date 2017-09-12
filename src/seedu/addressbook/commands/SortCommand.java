package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ":Displays all persons in alphabetical order." +
            "No parameter needed.\n" + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        addressBook.sortPerson();
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();

        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}

