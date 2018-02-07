package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents the street name in Person's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidStreet(String)}
 */
public class Street {

    public static final String EXAMPLE = "Clementi Ave 2";
    private static final String MESSAGE_STREET_CONSTRAINTS =
            "Street names should contain spaces or alphanumeric characters";
    private static final String STREET_VALIDATION_REGEX = "[\\p{Alnum} ]+";

    public final String value;

    public Street(String street){
        value = street;
    }

    /**
     * Returns true if the given string is a valid street name.
     */
    public static boolean isValidStreet(String test) throws IllegalValueException{
        if (!test.matches(STREET_VALIDATION_REGEX)){
            throw new IllegalValueException(MESSAGE_STREET_CONSTRAINTS);
        }
        return true;
    }

    @Override
    public String toString(){
        return value;
    }
}
