package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.ui.TextUi;

/**
 * Clears the address book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clears address book permanently.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";
    private static final String MESSAGE_FAILED = "Address book has not been cleared!";

    private static final String MESSAGE_CONFIRMATION = "Clear the address book? [Y/N]";

    private static final TextUi ui = new TextUi();

    public ClearCommand() {
    }


    @Override
    public CommandResult execute() {
        if(confirmed()){
            addressBook.clear();
            return new CommandResult(MESSAGE_SUCCESS);
        }else {
            return new CommandResult(MESSAGE_FAILED);
        }
    }

    private boolean confirmed() {
        ui.showToUser(MESSAGE_CONFIRMATION);
        final String userConfirmation = ui.getUserConfirmation();
        try {
            return new Parser().parseConfirmation(userConfirmation);
        }catch (IllegalValueException e){
            ui.showToUser(e.getMessage());
            return false;
        }
    }
}
