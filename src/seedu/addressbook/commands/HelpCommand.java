package seedu.addressbook.commands;

import static seedu.addressbook.TextUi.LS;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand implements Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions."
            + LS + "Example: " + COMMAND_WORD;

    public HelpCommand() {}

    @Override
    public CommandResult execute() {
        return new CommandResult(
                AddPersonCommand.MESSAGE_USAGE
                + LS + DeletePersonCommand.MESSAGE_USAGE
                + LS + ClearAddressBookCommand.MESSAGE_USAGE
                + LS + FindPersonsByWordsInNameCommand.MESSAGE_USAGE
                + LS + ListAllPersonsCommand.MESSAGE_USAGE
                + LS + ViewPersonDetailsCommand.MESSAGE_USAGE
                + LS + ViewAllPersonDetailsCommand.MESSAGE_USAGE
                + LS + HelpCommand.MESSAGE_USAGE
                + LS + ExitCommand.MESSAGE_USAGE
        );
    }
}
