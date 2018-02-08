package seedu.addressbook.data.person;

/**
 * Represents a Person's postal code
 */
public class PostalCode {
    private String postalCode;

    public PostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
