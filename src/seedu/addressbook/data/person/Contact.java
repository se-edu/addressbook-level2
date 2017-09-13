package seedu.addressbook.data.person;

public class Contact {

    public String value;
    protected boolean isPrivate;

    public Contact() {

    }

    public Contact(String value, boolean isPrivate) {
        this.value = value.trim();
        this.isPrivate = isPrivate;
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
