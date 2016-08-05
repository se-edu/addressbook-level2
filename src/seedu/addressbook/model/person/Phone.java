package seedu.addressbook.model.person;

import seedu.addressbook.Utils;
import seedu.addressbook.model.InvalidDataException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone extends ContactDetail {

    public static final String MESSAGE_PHONE_CONSTRAINTS = "Person phone numbers should only contain numbers";
    public static final String PHONE_VALIDATION_REGEX = "\\d+";

    public final String phoneNumber;

    /**
     * Validates given phone number.
     *
     * @throws InvalidDataException if given phone string is invalid.
     */
    public Phone(String phone, boolean isPrivate) throws InvalidDataException {
        super(isPrivate);
        Utils.assertNotNull(phone);
        phone = phone.trim();
        if (!isValidPhone(phone)) {
            throw new InvalidDataException(MESSAGE_PHONE_CONSTRAINTS);
        }
        this.phoneNumber = phone;
    }

    /**
     * Checks if a given string is a valid person phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(PHONE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return phoneNumber;
    }

}
