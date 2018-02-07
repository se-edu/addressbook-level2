package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents the postal code of a Person's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidPostalCode(String)}
 */
public class PostalCode {

    public static final String EXAMPLE = "424311";
    private static final String MESSAGE_POSTALCODE_CONSTRAINTS = "Postal codes should only contain numbers";
    private static final String POSTALCODE_VALIDATION_REGEX = "\\d+";

    public final String value;

    public PostalCode(String postalCode){
        value = postalCode;
    }

    /**
     * Returns true if the given string is a valid postal code.
     */
    public static boolean isValidPostalCode(String test) throws IllegalValueException{
        if (!test.matches(POSTALCODE_VALIDATION_REGEX)){
            throw new IllegalValueException(MESSAGE_POSTALCODE_CONSTRAINTS);
        }
        return true;
    }

    @Override
    public String toString() {
        return value;
    }
}
