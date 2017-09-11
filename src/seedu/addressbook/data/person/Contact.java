package seedu.addressbook.data.person;



public class Contact {

    public final String value;
    private boolean isPrivate;

    public Contact (String value, boolean isPrivate){
        this.value = value;
        this.isPrivate = isPrivate;
    }

    public String toString() {
        return value;
    }

    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

}
