package seedu.addressbook.data.address;

/**
 * Represents a street in an address.
 */
public class Street {
    private final String street;

    public Street(String street) {
        this.street = street;
    }

    public String toString() {
        return this.street;
    }
}