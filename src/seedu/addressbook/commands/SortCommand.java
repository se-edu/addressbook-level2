package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_SORT_ACKNOWEDGEMENT = "The have been sorted.";

    public CommandResult execute() {
        this.addressBook.sort();
        //List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(MESSAGE_SORT_ACKNOWEDGEMENT);
    }


}
