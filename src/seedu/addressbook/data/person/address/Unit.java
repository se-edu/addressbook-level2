package seedu.addressbook.data.person.address;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 *
 * Represents the unit number of an address.
 *
 */
public class Unit {
    public static final String EXAMPLE = "12-34";
    public static final String MESSAGE_UNIT_CONSTRAINTS = "Unit should be in the format of #x.x, where x represents a series of digits.";
    public static final String UNIT_VALIDATION_REGEX = "#\\d+-\\d+";

    private String value;

    public Unit(String unit) throws IllegalValueException {
        String trimmedUnit = unit.trim();
        if (!isValidUnit(unit)) {
            throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
        }
        this.value = trimmedUnit;
    }

    /**
     * Returns true if the given string is a valid person name.
     */
    public static boolean isValidUnit(String test) {
        return test.matches(UNIT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.value.equals(((Unit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
