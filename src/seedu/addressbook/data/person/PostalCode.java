package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class PostalCode {
    public static final String EXAMPLE = "349886";
    public static final String MESSAGE_POSTALCODE_CONSTRAINTS = "Person PostalCodees can be in any format";
    public static final String POSTALCODE_VALIDATION_REGEX = "(\\d{6})$";

    public String value;
    protected boolean isPrivate;

    /**
     * Validates given PostalCode.
     *
     * @throws IllegalValueException if given PostalCode string is invalid.
     */
    public PostalCode(String PostalCode, boolean isPrivate) throws IllegalValueException {
        String trimmedPostalCode = PostalCode.trim();
        this.isPrivate = isPrivate;
        if (!isValidPostalCode(trimmedPostalCode)) {
            throw new IllegalValueException(MESSAGE_POSTALCODE_CONSTRAINTS);
        }
        this.value = trimmedPostalCode;
    }

    /**
     * Returns true if a given string is a valid person PostalCode.
     */
    public static boolean isValidPostalCode(String test) {
        return test.matches(POSTALCODE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PostalCode // instanceof handles nulls
                && this.value.equals(((PostalCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
