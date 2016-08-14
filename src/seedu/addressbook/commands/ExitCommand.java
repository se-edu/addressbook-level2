package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.Utils;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.TextUi.LS;

/**
 * Terminates the program.
 */
public class ExitCommand implements Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program."
            + LS + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Exiting Addressbook as requested ...";

    private TextUi ui;

    public ExitCommand() {}

    @Override
    public CommandResult execute() {
        return new CommandResult(MESSAGE_EXIT_ACKNOWEDGEMENT);
    }

    public static boolean isExit(Command command) {
        return command != null && command instanceof ExitCommand;
    }
}
