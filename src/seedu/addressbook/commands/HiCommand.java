package seedu.addressbook.commands;

/**
 * Adds a person to the address book.
 */
public class HiCommand extends Command {

    public static final String COMMAND_WORD = "hi";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Hi! ";

    public static final String MESSAGE_SUCCESS = "Hi!";

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
