package seedu.addressbook.commands;


/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE
                + "\n\n" + DeleteCommand.MESSAGE_USAGE
                + "\n\n" + ClearCommand.MESSAGE_USAGE
                + "\n\n" + FindCommand.MESSAGE_USAGE
                + "\n\n" + ListCommand.MESSAGE_USAGE
                + "\n\n" + ViewCommand.MESSAGE_USAGE
                + "\n\n" + ViewAllCommand.MESSAGE_USAGE
                + "\n\n" + HelpCommand.MESSAGE_USAGE
                + "\n\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
