package seedu.addressbook.data.person.address;

public class PostalCode {

    private String postalCodeNumber;

    public PostalCode(String postalCode){
        postalCodeNumber = postalCode;
    }
    public String getPostalCode(){
        return postalCodeNumber;
    }
}
