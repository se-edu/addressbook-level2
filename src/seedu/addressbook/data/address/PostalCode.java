package seedu.addressbook.data.address;

/**
 * Represents a postal code in an address.
 */
public class PostalCode {
    private final String postalCode;

    public PostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String toString() {
        return this.postalCode;
    }
}