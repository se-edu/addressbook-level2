package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
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
            + " 1 p/23456789 e/johndoe@gmail.com a/311, Clementi Ave 2, #02-25";

    private static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    private static final String MESSAGE_EDIT_ARGS_INVALID = "Edit command's arguments are invalid. Please check.";

    private static final int EDIT_PERSON_DATA_COUNT = 4;

    private static final int EDIT_PERSON_DATA_INDEX_NAME = 0;
    private static final int EDIT_PERSON_DATA_INDEX_PHONE = 1;
    private static final int EDIT_PERSON_DATA_INDEX_EMAIL = 2;
    private static final int EDIT_PERSON_DATA_INDEX_ADDRESS = 3;

    private final String[] newData;
    private final boolean[] newPrivacyStatus;

    public EditCommand(int targetVisibleIndex,
                       String phone, boolean isPhonePrivate,
                       String email, boolean isEmailPrivate,
                       String address, boolean isAddressPrivate) throws IllegalValueException {
        super(targetVisibleIndex);
        this.newData = makeNewData("", phone, email, address);
        this.newPrivacyStatus = makeNewPrivacyStatus(isPhonePrivate, isEmailPrivate, isAddressPrivate);
    }

    private static String[] makeNewData(String name, String phone, String email, String address) {
        final String[] data = new String[EDIT_PERSON_DATA_COUNT];
        data[EDIT_PERSON_DATA_INDEX_NAME] = name;
        data[EDIT_PERSON_DATA_INDEX_PHONE] = phone;
        data[EDIT_PERSON_DATA_INDEX_EMAIL] = email;
        data[EDIT_PERSON_DATA_INDEX_ADDRESS] = address;
        return data;
    }

    private static boolean[] makeNewPrivacyStatus(boolean isPhonePrivate, boolean isEmailPrivate, boolean isAddressPrivate) {
        final boolean[] privacyStatus = new boolean[EDIT_PERSON_DATA_COUNT];
        privacyStatus[EDIT_PERSON_DATA_INDEX_PHONE] = isPhonePrivate;
        privacyStatus[EDIT_PERSON_DATA_INDEX_EMAIL] = isEmailPrivate;
        privacyStatus[EDIT_PERSON_DATA_INDEX_ADDRESS] = isAddressPrivate;
        return privacyStatus;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.editPerson(target, newData, newPrivacyStatus);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (IllegalValueException ive) {
            return new CommandResult(MESSAGE_EDIT_ARGS_INVALID);
        }
    }
}