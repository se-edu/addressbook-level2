package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

    /**
     * Represents a Person's address' unit in the address book.
     * Guarantees: immutable; is valid as declared in {@link #isValidUnit(String)}
     */
public class Unit {

    public static final String EXAMPLE = "#12-34";
    public static final String MESSAGE_UNIT_CONSTRAINTS =
            "Person address' unit should start with #, followed by 2 numbers separated by '-'.";
    public static final String UNIT_VALIDATION_REGEX = "#[\\d]+-[\\d]+";

    public final String value;


    /**
     * Validates given address unit.
     *
     * @throws IllegalValueException if given block string is invalid.
     */
    Unit(String unit) throws IllegalValueException {
        if(!isValidUnit(unit)){
            throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
        }

        this.value = unit;
    }

    public static boolean isValidUnit(String test) {
        return test.matches(UNIT_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}


