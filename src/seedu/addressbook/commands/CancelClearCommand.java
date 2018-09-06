package seedu.addressbook.commands;

public class CancelClearCommand extends Command {

    public static final String COMMAND_WORD = "no";
    public static final String MESSAGE_CANCEL = "Address book has not been cleared.";

    public CancelClearCommand() {}

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_CANCEL);
    }
}