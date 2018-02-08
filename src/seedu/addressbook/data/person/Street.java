package seedu.addressbook.data.person;

/**
 *  Represents a Person's street
 */
public class Street {
    private String street;

    public Street(String street) {
        this.street = street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreet() {
        return street;
    }
}
