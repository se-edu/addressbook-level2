package seedu.addressbook.data.person;

public class Contact {

    public String value;
    protected boolean isPrivate;

    protected Contact() {
    }

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
