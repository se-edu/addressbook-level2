package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the address book. "
            + "Sorting takes place using alphabetical order.\n"
            + "Parameters: NONE";

    public static final String MESSAGE_SUCCESS = "Address book sorted.";

    public SortCommand() throws Exception {

    }

    @Override
    public CommandResult execute() {
        try {
            addressBook.sortAddressBook();
            return new CommandResult(MESSAGE_SUCCESS);
        } catch (Exception e) {
            return new CommandResult("Error sorting.");
        }
    }

}
