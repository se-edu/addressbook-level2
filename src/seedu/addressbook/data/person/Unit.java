package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Unit {
    public static final String EXAMPLE = "#12-31";
    public static final String MESSAGE_UNIT_CONSTRAINTS = "Unit has to be in numerals";
    public static final String UNIT_VALIDATION_REGEX = "\\d+";

    public String value;
    protected boolean isPrivate;

    /**
     * Validates given Unit.
     *
     * @throws IllegalValueException if given Unit string is invalid.
     */
    public Unit(String Unit, boolean isPrivate) throws IllegalValueException {
        String trimmedUnit = Unit.trim();
        this.isPrivate = isPrivate;
        if (!isValidUnit(trimmedUnit)) {
            throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
        }
        this.value = trimmedUnit;
    }

    /**
     * Returns true if a given string is a valid person Unit.
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
                || (other instanceof Unit // instanceof handles nulls
                && this.value.equals(((Unit) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
