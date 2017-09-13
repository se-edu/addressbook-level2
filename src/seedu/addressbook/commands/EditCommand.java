package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Edits a person's info in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a person's info the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n"
            + "Parameters: INDEX [p]p/PHONE (or) [p]e/EMAIL (or) [p]a/ADDRESS\n"
            + "Example: " + COMMAND_WORD
            + " 1 p/23456789 (or) e/johndoe@gmail.com (or) a/311, Clementi Ave 2, #02-25";

    private static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    private static final String MESSAGE_EDIT_ARGS_INVALID = "Edit command's arguments are invalid. Please check.";

    private static final int EDIT_PERSON_DATA_COUNT = 4;

    private static final int EDIT_PERSON_DATA_INDEX_NAME = 0;
    private static final int EDIT_PERSON_DATA_INDEX_PHONE = 1;
    private static final int EDIT_PERSON_DATA_INDEX_EMAIL = 2;
    private static final int EDIT_PERSON_DATA_INDEX_ADDRESS = 3;

    private final Object[] newPersonData;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(int targetVisibleIndex,
                       String phone, boolean isPhonePrivate,
                       String email, boolean isEmailPrivate,
                       String address, boolean isAddressPrivate) throws IllegalValueException {
        super(targetVisibleIndex);
        this.newPersonData = new Object[EDIT_PERSON_DATA_COUNT];
        if (!phone.equals("")) this.newPersonData[EDIT_PERSON_DATA_INDEX_PHONE] = new Phone(phone, isPhonePrivate);
        if (!email.equals("")) this.newPersonData[EDIT_PERSON_DATA_INDEX_EMAIL] = new Email(email, isEmailPrivate);
        if (!address.equals("")) this.newPersonData[EDIT_PERSON_DATA_INDEX_ADDRESS] = new Address(address, isAddressPrivate);
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.editPerson(target, newPersonData);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}