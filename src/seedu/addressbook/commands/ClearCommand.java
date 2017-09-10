package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.ui.TextUi;

/**
 * Clears the address book.
 */
public class ClearCommand extends ConfirmableCommand{

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clears address book permanently.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    private static final String MESSAGE_FAILED = "Address book has not been cleared!";

    private static final String MESSAGE_CONFIRMATION = "Clear the address book?";

    public ClearCommand() {
        super(MESSAGE_CONFIRMATION);
    }

    @Override
    public CommandResult confirmed() {
        addressBook.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public CommandResult rejected() {
        return new CommandResult(MESSAGE_FAILED);
    }
}
