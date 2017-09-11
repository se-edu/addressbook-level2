package seedu.addressbook.commands;


/**
 * Lists all persons in the address book to the user.
 */

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the address book by the name of the person.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "List sorted successfully";



    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
