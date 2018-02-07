package seedu.addressbook.data.person;

public class Street {
    private String street;

    Street(String s){
        street = s;
    }

    public String getStreet(){
        return street;
    }

    public void setStreet(String s){
        this.street = s;
    }
}
