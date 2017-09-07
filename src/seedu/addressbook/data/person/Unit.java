package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Unit {

    public static final String EXAMPLE = "123";
    public static final String MESSAGE_UNIT_CONSTRAINTS = "Unit should only contain numbers in XXXXXX or XX-XXX format";
    public static final String UNIT_VALIDATION_REGEX = "\\d{0,3}\\-?\\d{0,5}";
    public final String value;

    public Unit(String unit) throws IllegalValueException {
        if (!isValidUnit(unit)) {
            throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
        }
        this.value = unit;
    }

    private boolean isValidUnit(String test)  {
        return (test.matches(UNIT_VALIDATION_REGEX));
    }

    @Override
    public String toString() {
        return value;
    }
}
