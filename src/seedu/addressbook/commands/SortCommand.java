package seedu.addressbook.commands;

/**
 * Sorts all the persons in the address book by name in alphabetical order
 */

public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": arrange everyone in the address book in alphabetical order.";

    public static final String MESSAGE_SUCCESS = "Successfully sorted!";


    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
