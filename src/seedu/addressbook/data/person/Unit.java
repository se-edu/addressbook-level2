package seedu.addressbook.data.person;

/**
 * Represents a unit in a Person's Address
 */
public class Unit {

    private final String value; // empty "" string if user did not specify unit

    public Unit(String unit) {
        value = unit.trim();
    }

    public String getValue() { return value; }

    @Override
    public String toString() { return value; }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceOf handles null
                && this.value.equals(((Unit) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
