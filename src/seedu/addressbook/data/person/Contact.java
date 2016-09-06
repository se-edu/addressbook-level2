package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's data element in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 * Cannot be instantiated directly
 */
public abstract class Contact {
    
    public final String value;
    private boolean isPrivate;

    /**
     * Validates given data based on argument regex and constraints.
     *
     * @throws IllegalValueException if given data string is invalid.
     */
    public Contact(String dat, boolean isPrivate, boolean toTrim, String regex, String constraints) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if(toTrim) {
            dat = dat.trim();
        }
        if (!isValid(dat, regex)) {
            throw new IllegalValueException(constraints);
        }
        this.value = dat;
    }
    
    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValid(String test, String regex) {
        return test.matches(regex);
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
