package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's contact in the address book.
 * Parent class for Address, Email and Phone
 */
public class Contact {
    public String value;
    protected boolean isPrivate;
    protected String regex;
    protected String constraints;

    /**
     * Constructs the Contact Object
     *
     * @throws IllegalValueException if given value is invalid.
     */
    public Contact(String value, boolean isPrivate, String regex, String constraints) throws IllegalValueException {
        String trimmedValue = value.trim();
        setProperties(trimmedValue, isPrivate, regex, constraints);

        if (!isValid(trimmedValue)) {
            throw new IllegalValueException(constraints);
        }
    }

    /**
     * Returns true if the given string is a valid contact depending on the regex.
     */
    private boolean isValid(String test) {
        return test.matches(regex);
    }

    /** Sets the properties of the Contact object*/
    protected void setProperties(String value, boolean isPrivate, String regex, String constraints) {
        this.value = value;
        this.isPrivate = isPrivate;
        this.constraints = constraints;
        this.regex = regex;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Contact // instanceof handles nulls
                && this.value.equals(((Contact) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
