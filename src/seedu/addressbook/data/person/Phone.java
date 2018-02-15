package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's phone number in the address book.
 * Phone number can only contain exactly 8 numerical characters
 * Guarantees: immutable; is valid as declared in {@link #isValidPhone(String)}
 */
public class Phone {

    public static final String EXAMPLE = "12345678";
    public static final String MESSAGE_PHONE_CONSTRAINTS = "Person phone numbers should only contain exactly 8 numbers";
    public static final String PHONE_VALIDATION_REGEX = "\\d{8}";

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Phone(String phone, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        String trimmedPhone = phone.trim();
        if (!isValidPhone(trimmedPhone)) {
            throw new IllegalValueException(MESSAGE_PHONE_CONSTRAINTS);
        }
        this.value = trimmedPhone;
    }

    /**
     * Returns true if the given string is a valid person phone number.
     */
    public static boolean isValidPhone(String test) {
        return test.matches(PHONE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Phone // instanceof handles nulls
                && this.value.equals(((Phone) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
