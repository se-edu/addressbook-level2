package seedu.addressbook.data.person.address;

/**
 *
 * Represents the street of an address.
 *
 */
public class Street {
    public static final String EXAMPLE = "Clementi Ave 3";
    private String value;

    public Street(String street) {
        String trimmedStreet = street.trim();
        this.value = street;
    }


    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.value.equals(((Street) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
