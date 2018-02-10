package seedu.addressbook.data.person;

public class Street {
    public final String street;

    public Street(String street){
        this.street = street;
    }

    public String toString(){
        return "Street: " + street;
    }
}
