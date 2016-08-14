package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.Utils;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.List;

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

    public ClearAddressBookCommand() {}

    @Override
    public void injectDependencies(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
    }

    @Override
    public CommandResult execute() {
        Utils.assertNotNull(addressBook);
        addressBook.clear();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
