package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.Utils;
import seedu.addressbook.model.AddressBook;

import static seedu.addressbook.TextUi.*;

/**
 * Clears the address book.
 */
public class ClearAddressBookCommand implements Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_USAGE = "Clears address book permanently."
            + LS + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";

    private AddressBook addressBook;

    /**
     * @param addressBook to be cleared
     */
    public ClearAddressBookCommand(AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public void injectDependencies(TextUi ui, AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public String execute() {
        Utils.assertNotNull(addressBook);
        addressBook.clear();
        return MESSAGE_SUCCESS;
    }
}
