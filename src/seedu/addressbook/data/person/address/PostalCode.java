package seedu.addressbook.data.person.address;

import seedu.addressbook.data.exception.IllegalValueException;

public class PostalCode {

    public static final String EXAMPLE = "257";
    public static final String MESSAGE_POSTALCODE_CONSTRAINTS = "Address Postal Code numbers can be omitted";
    public static final String POSTALCODE_VALIDATION_REGEX = ".*";

    public final String value;

    /**
     * Validates given postal code number.
     *
     * @throws IllegalValueException if given postal code string is invalid.
     */
    public PostalCode(String code) throws IllegalValueException {
        String trimmedCode = code.trim();
        if (!isValidCode(trimmedCode)) {
            throw new IllegalValueException(MESSAGE_POSTALCODE_CONSTRAINTS);
        }
        this.value = trimmedCode;
    }

    /**
     * Returns true if the given string is a valid address postal code number.
     */
    public static boolean isValidCode(String test) {
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

}
