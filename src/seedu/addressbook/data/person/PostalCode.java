package seedu.addressbook.data.person;
/**
 * Represents a Postal Code in a Person's address in the address book.
 */

public class PostalCode {
    private String postalCodeNumber;

    public PostalCode() {
        postalCodeNumber = "";
    }

    public void setPostalCodeNumber(String p) {
        postalCodeNumber = p;
    }

    public String getPostalCodeNumber() {
        return postalCodeNumber;
    }
}
