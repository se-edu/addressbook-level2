package seedu.addressbook.commands;

public class ConfirmCommand extends Command {
    public static final String COMMAND_WORD = "confirm";

    public static final String MESSAGE_WARN = "Your command may cause invertible harm to the data storage."
            + "\nType 'confirm' to confirm.";

    public ConfirmCommand() {}


    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_WARN);
    }
}
