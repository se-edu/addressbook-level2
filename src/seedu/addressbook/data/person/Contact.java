package seedu.addressbook.data.person;


public class Contact {
    
    
    
    public final String value;
    protected boolean isPrivate;
    
    public Contact(String value, boolean isPrivate){
        this.value = value;
        this.isPrivate = isPrivate;
    }
    
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
    public String toString() {
        return value;
    }

}
