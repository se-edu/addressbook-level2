package seedu.addressbook.data.person;

/**
 * Represents street name in a Person's Address
 */
public class Street {

    private final String value;

    public Street(String street) {
        value = street.trim();
    }

    public String getValue() { return value; }

    @Override
    public String toString() { return value; }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Street // instanceOf handles null
                && this.value.equals(((Street) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
