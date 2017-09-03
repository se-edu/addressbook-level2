package seedu.addressbook.data.person;
/**
 * Represents a Street in a Person's address in the address book.
 */

public class Street {
    private String streetNumber;

    public Street() {
        streetNumber = "";
    }

    public void setStreetNumber(String s) {
        streetNumber = s;
    }

    public String getStreetNumber() {
        return streetNumber;
    }
}
