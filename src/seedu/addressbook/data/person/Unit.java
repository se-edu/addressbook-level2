package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents the unit number in a Person's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidUnit(String)}
 */
public class Unit {

    public static final String EXAMPLE = "#02-25";
    private static final String MESSAGE_UNIT_CONSTRAINTS =
            "Unit numbers should start with '#'and be two numbers seperated by'-'";
    private static final String UNIT_VALIDATION_REGEX = "#\\d+-\\d+";

    public final String value;

    public Unit(String unit){
        value = unit;
    }

    /**
     * Returns true if the given string is a valid unit number.
     */
    public static boolean isValidUnit(String test) throws IllegalValueException{
        if(!test.matches(UNIT_VALIDATION_REGEX)){
            throw new IllegalValueException(MESSAGE_UNIT_CONSTRAINTS);
        }
        return true;
    }

    @Override
    public String toString(){
        return value;
    }
}
