package seedu.addressbook.data.person;

/**
 * Represents a postal code in a Person's Address
 */
public class PostalCode {

    private final String value;

    public PostalCode(String postalCode) {
        value = postalCode.trim();
    }

    public String getValue() { return value; }

    @Override
    public String toString() { return value; }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PostalCode // instanceOf handles null
                && this.value.equals(((PostalCode) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
