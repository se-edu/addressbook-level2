package seedu.addressbook.data.person;

/**
 * Represents a Person's street address in the address book.
 * Guarantees: immutable;
 */

public class Street {
    //public final String value;
    private boolean isPrivate;
    public final String value;

    public Street(String streetAddress, boolean isPrivate) {
        String trimmedStreetAddress = streetAddress.trim();
        this.isPrivate = isPrivate;
        this.value = trimmedStreetAddress;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Street // instanceof handles nulls
                && this.value.equals(((Street) other).value)); // state check
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
