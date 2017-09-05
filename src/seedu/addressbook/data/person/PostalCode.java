package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address' Postal Code in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidUnit(String)}
 */
public class PostalCode {

    public static final String EXAMPLE = "123456";
    public static final String MESSAGE_POSTAL_CODE_CONSTRAINTS =
            "Person address' unit should contain only numbers";
    public static final String POSTAL_CODE_VALIDATION_REGEX = "\\d+";

    public final String value;


    /**
     * Validates given address unit.
     *
     * @throws IllegalValueException if given block string is invalid.
     */
    PostalCode(String postalCode) throws IllegalValueException {
        if (!isValidUnit(postalCode)) {
            throw new IllegalValueException(MESSAGE_POSTAL_CODE_CONSTRAINTS);
        }

        this.value = postalCode;
    }

    public static boolean isValidUnit(String test) {
        return test.matches(POSTAL_CODE_VALIDATION_REGEX);
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