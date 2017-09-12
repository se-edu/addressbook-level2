package seedu.addressbook.data.person;

/**
 * A super class present Person's contact, including Phone, Email and Address
 */
public class Contact {

    public String value;
    protected boolean isPrivate;

    public Contact() {
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
