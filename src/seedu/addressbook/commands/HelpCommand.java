package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Shows help instructions.
 */
public class HelpCommand {
    protected AddressBook addressBook;
    protected List<? extends ReadOnlyPerson> relevantPersons;

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public HelpCommand() {}

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        return new CommandResult(
                AddCommand.MESSAGE_USAGE
                + "\n" + DeleteCommand.MESSAGE_USAGE
                + "\n" + ClearCommand.MESSAGE_USAGE
                + "\n" + FindCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + ViewCommand.MESSAGE_USAGE
                + "\n" + ViewAllCommand.MESSAGE_USAGE
                + "\n" + HelpCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
        this.relevantPersons = relevantPersons;
    }
}
