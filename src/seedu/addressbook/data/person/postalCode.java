package seedu.addressbook.data.person;

public class postalCode {
    private String postalcode;

    postalCode(String p){
        postalcode = p;
    }
    public String getPostalcode(){
        return postalcode;
    }

    public void setPostalcode(String p){
        this.postalcode = p;
    }
}
