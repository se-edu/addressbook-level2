package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Street {

    public static final String EXAMPLE = "123456";
    public static final String MESSAGE_STREETNAME_CONSTRAINTS = "Postal Code should only contain numbers in XXXXXX format";
    public final String value;

    public Street(String street) throws IllegalValueException {
        if (!isValidStreet(street)) {
            throw new IllegalValueException(MESSAGE_STREETNAME_CONSTRAINTS);
        }
        this.value = street;
    }

    private boolean isValidStreet(String street) {
        return value.getClass().equals(String.class);
    }

    @Override
    public String toString() {
        return value;
    }
}
