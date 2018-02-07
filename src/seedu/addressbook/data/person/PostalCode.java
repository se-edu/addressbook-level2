package seedu.addressbook.data.person;

public class PostalCode {
    public final String postalCode;

    public PostalCode(String postalCode){
        this.postalCode = postalCode;
    }

    public String toString(){
        return "Postal Code: " + postalCode;
    }
}
