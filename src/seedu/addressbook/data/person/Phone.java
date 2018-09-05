package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidContact(String)}
 */
public class Phone extends Contact implements Printable{

    public static final String EXAMPLE = "123456789";
    public static String MESSAGE_PHONE_CONSTRAINTS = "Person phone numbers should only contain numbers";
    public static String PHONE_VALIDATION_REGEX = "\\d+";

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Phone(String phone, boolean isPrivate) throws IllegalValueException {
        super(phone, isPrivate);
        if (!isValidContact(value)) {
            throw new IllegalValueException(MESSAGE_PHONE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if the given string is a valid person phone number.
     */
    @Override
    public boolean isValidContact(String test) {
        return test.matches(PHONE_VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && this.value.equals(((Phone) other).value)); // state check
    }

    @Override
    public String getPrintableString() {
        return "Phone: " + value;
    }
}