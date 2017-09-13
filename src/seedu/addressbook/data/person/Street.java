package seedu.addressbook.data.person;

public class Street {
    private String street;
    public Street(String str) {
        this.street = str;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String streetName) {
        this.street = streetName;
    }
}