package seedu.addressbook.data.person;

public abstract class Contact {
    public String value;
    protected boolean isPrivate;
    
    protected Contact() { }
    
    public Contact(String value, boolean isPrivate) {
        this.isPrivate = isPrivate;
        this.value = value;
    }
    
    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
