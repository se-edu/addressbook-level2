package seedu.addressbook.data.person;

public class PostalCode {
    private String postalCode;

    PostalCode(String p){
        postalCode = p;
    }
    public String getPostalCode(){
        return postalCode;
    }

    public void setPostalCode(String p){
        this.postalCode = p;
    }
}
