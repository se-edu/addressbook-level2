package seedu.addressbook.data.person.address;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 *
 * Represents the postal code of an address.
 *
 */
public class PostalCode {
    public static final String EXAMPLE = "231534";
    public static final String MESSAGE_POSTALCODE_CONSTRAINTS = "Postal code should only consist of 6 numbers.";
    public static final String POSTALCODE_VALIDATION_REGEX = "\\d{6}+";
    private String value;

    public PostalCode(String postalCode) throws IllegalValueException {
        String trimmedPostalCode = postalCode.trim();
        if (!isValidPostalCode(postalCode)) {
            throw new IllegalValueException(MESSAGE_POSTALCODE_CONSTRAINTS);
        }
        this.value = trimmedPostalCode;
    }

    /**
     * Returns true if the given string is a valid person name.
     */
    public static boolean isValidPostalCode(String test) {
        return test.matches(POSTALCODE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.value.equals(((PostalCode) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
