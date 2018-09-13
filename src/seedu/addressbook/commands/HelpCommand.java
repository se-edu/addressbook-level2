package seedu.addressbook.commands;


import seedu.addressbook.Main;

/**
 * Shows help instructions.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public HelpCommand() {}

    @Override
    public CommandResult execute() {
        if(!Main.isUserInputHelp) {
            System.out.println("Command not found, for more help, please enter 'help' to the CLI");
        } else {
            return new CommandResult(
                    "\n" + AddCommand.MESSAGE_USAGE
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
        return new CommandResult(HelpCommand.MESSAGE_USAGE);
    }
}
