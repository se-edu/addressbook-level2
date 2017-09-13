package seedu.addressbook.data.person;

public class Contact {

    public final String value;

    public Contact(String contact) {
        String trimmedContact = contact.trim();
        this.value = trimmedContact;
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