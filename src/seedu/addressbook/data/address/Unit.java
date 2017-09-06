package seedu.addressbook.data.address;

/**
 * Represents a unit in an address.
 */
public class Unit {
    private final String unit;

    public Unit(String unit) {
        this.unit = unit;
    }

    public String toString() {
        return this.unit;
    }
}