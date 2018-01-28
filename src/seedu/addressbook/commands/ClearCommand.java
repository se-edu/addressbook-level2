package seedu.addressbook.commands;

import seedu.addressbook.data.AddressBook;

/**
 * Clears the address book.
 */
public class ClearCommand {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clears address book permanently.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    public ClearCommand() {}

    public CommandResult execute(AddressBook addressBook) {
        addressBook.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
