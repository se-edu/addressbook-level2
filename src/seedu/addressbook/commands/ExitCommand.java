package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Terminates the program.
 */
public class ExitCommand {
    protected AddressBook addressBook;
    protected List<? extends ReadOnlyPerson> relevantPersons;

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Exiting Address Book as requested ...";

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
        this.relevantPersons = relevantPersons;
    }

    public static boolean isExit(String commandWord) {
        return commandWord.equals(COMMAND_WORD);
    }
}
