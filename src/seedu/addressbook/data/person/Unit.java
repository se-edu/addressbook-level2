package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUnit(String)}
 */
public class Unit {

    public static final String EXAMPLE = "123, some street, unit, postal code";
    public static final String MESSAGE_UNIT_CONSTRAINTS = "Person postal code can be in any format";
    public static final String UNIT_VALIDATION_REGEX = ".+";

    private final String value;
    
    private boolean isPrivate;
    
    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Unit(String unit, boolean isPrivate) throws IllegalValueException {
        String trimmedUnit = unit.trim();
        this.isPrivate = isPrivate;
        if (!isValidUnit(trimmedUnit)) {
            throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
     
        }
        this.value = trimmedUnit;
  
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidUnit(String test) {
        return test.matches(UNIT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
    	return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit
                && this.toString().equals(((Unit) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
