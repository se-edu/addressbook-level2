package seedu.addressbook.model.person;

import seedu.addressbook.Utils;
import seedu.addressbook.model.InvalidDataException;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String MESSAGE_NAME_CONSTRAINTS = "Person names should only contain alphabetic characters";
    public static final String NAME_VALIDATION_REGEX = "\\p{Alpha}+";

    public final String fullName;

    /**
     * Validates given name.
     *
     * @throws InvalidDataException if given name string is invalid.
     */
    public Name(String name) throws InvalidDataException {
        Utils.assertNotNull(name);
        if (!isValidName(name)) {
            throw new InvalidDataException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.fullName = name;
    }

    /**
     * Checks if a given string is a valid person name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return fullName;
    }

}
