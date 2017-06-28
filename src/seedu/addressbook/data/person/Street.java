package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBlock(String)}
 */
public class Street {

    public static final String EXAMPLE = "123, some street, unit, postal code";
    public static final String MESSAGE_STREET_CONSTRAINTS = "Person street can be in any format";
    public static final String STREET_VALIDATION_REGEX = ".+";

    private final String value;
    
    private boolean isPrivate;
    
    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Street(String street, boolean isPrivate) throws IllegalValueException {
        String trimmedStreet = street.trim();
        this.isPrivate = isPrivate;
        if (!isValidStreet(trimmedStreet)) {
            throw new IllegalValueException(MESSAGE_STREET_CONSTRAINTS);
     
        }
        this.value = trimmedStreet;
  
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidStreet(String test) {
        return test.matches(STREET_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
    	return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Street
                && this.toString().equals(((Street) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
