package seedu.addressbook.data.person;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address' street in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStreet(String)}
 */
public class Street {

    public static final String EXAMPLE = "Clementi Ave 3";
    public static final String MESSAGE_STREET_CONSTRAINTS =
            "Person address' street should be alphanumeric";
    
    //TODO: better regex validation
    public static final String STREET_VALIDATION_REGEX = "[A-Za-z0-9]";

    public final String value;


    /**
     * Validates given address block.
     *
     * @throws IllegalValueException if given block string is invalid.
     */
    Street(String street) throws IllegalValueException {
        if(!isValidStreet(street)){
            throw new IllegalValueException(MESSAGE_STREET_CONSTRAINTS);
        }

        this.value = street;
    }

    public static boolean isValidStreet(String test) {
        return test.matches(STREET_VALIDATION_REGEX);
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
