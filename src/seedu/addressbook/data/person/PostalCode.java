package seedu.addressbook.data.person;

public class PostalCode {
    String postalCode = new String();
    public PostalCode(String postalCode){
        this.postalCode = postalCode;
    }
    public String getPostalCode(){
        return postalCode;
    }
}
