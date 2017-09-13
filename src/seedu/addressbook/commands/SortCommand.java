package seedu.addressbook.commands;

import java.util.List;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = "sort: Displays all persons in the address book as a list in alphabetical order.\nExample: sort";

    public SortCommand() {
    }

    public CommandResult execute() {
        List var1 = this.addressBook.getAllPersons().sortedListView();
        return new CommandResult(getMessageForPersonListShownSummary(var1), var1);
    }
}
