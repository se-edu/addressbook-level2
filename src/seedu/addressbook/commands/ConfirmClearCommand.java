package seedu.addressbook.commands;

public class ConfirmClearCommand extends Command {

    public static final String COMMAND_WORD = "yes";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public ConfirmClearCommand() {}

    @Override
    public CommandResult execute() {
        addressBook.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}