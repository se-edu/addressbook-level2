package seedu.addressbook.data.person;

public class Postal {
    private final String postalCode;

    public Postal(String code){
        postalCode = code;
    }
    public String getPostalCode(){
        return postalCode;
    }
}
