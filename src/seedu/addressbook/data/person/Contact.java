package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Contact {
	
    public final String value;
    private boolean isPrivate;
    
    public Contact(String contact, boolean isPrivate, String messageConstraints, String validationRegex) throws IllegalValueException {
        this.isPrivate = isPrivate;
        contact = contact.trim();
        if (!isValidContact(contact, validationRegex)) {
            throw new IllegalValueException(messageConstraints);
        }
        this.value = contact;
    }
    
    public static boolean isValidContact(String test, String validationRegex) {
        return test.matches(validationRegex);
    }
    
    public String toString() {
        return value;
    }

    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Contact // instanceof handles nulls
                && this.value.equals(((Contact) other).value)); // state check
    }
    
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
