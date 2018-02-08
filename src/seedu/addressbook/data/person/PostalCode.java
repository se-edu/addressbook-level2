package seedu.addressbook.data.person;

/**
 * Represents a Person's postalCode address in the address book.
 * Guarantees: immutable;
 */

public class PostalCode {
    //public final String value;
    private boolean isPrivate;
    public final String value;

    public PostalCode(String postalCodeAddress, boolean isPrivate) {
        String trimmedPostalCodeAddress = postalCodeAddress.trim();
        this.isPrivate = isPrivate;
        this.value = trimmedPostalCodeAddress;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PostalCode // instanceof handles nulls
                && this.value.equals(((PostalCode) other).value)); // state check
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}