package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public abstract class Contact {

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given phone number.
     *
     * @throws IllegalValueException if given phone string is invalid.
     */
    public Contact(String phone, boolean isPrivate) {
        this.isPrivate = isPrivate;
        String trimmedPhone = phone.trim();
        this.value = trimmedPhone;
    }

    public abstract boolean isValidContact(String test);

    @Override
    public String toString() {
        return value;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}