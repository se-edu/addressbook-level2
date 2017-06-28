package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidPostalCode(String)}
 */
public class PostalCode {

    public static final String EXAMPLE = "123, some street, unit, postal code";
    public static final String MESSAGE_POSTALCODE_CONSTRAINTS = "Person postal code can be in any format";
    public static final String POSTALCODE_VALIDATION_REGEX = ".+";

    private final String value;
    
    private boolean isPrivate;
    
    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public PostalCode(String postalCode, boolean isPrivate) throws IllegalValueException {
        String trimmedPostalCode = postalCode.trim();
        this.isPrivate = isPrivate;
        if (!isValidPostalCode(trimmedPostalCode)) {
            throw new IllegalValueException(MESSAGE_POSTALCODE_CONSTRAINTS);
     
        }
        this.value = trimmedPostalCode;
  
    }

    /**
     * Returns true if a given string is a valid person address.
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
                || (other instanceof PostalCode
                && this.toString().equals(((PostalCode) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
