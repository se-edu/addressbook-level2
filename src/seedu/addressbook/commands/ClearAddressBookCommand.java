package seedu.addressbook.commands;

import seedu.addressbook.util.Utils;

import static seedu.addressbook.TextUi.LS;

/**
 * Clears the address book.
 */
public class ClearAddressBookCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clears address book permanently."
            + LS + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public ClearAddressBookCommand() {}


    @Override
    public CommandResult execute() {
        Utils.assertNotNull(addressBook);
        addressBook.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
