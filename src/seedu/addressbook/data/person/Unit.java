package seedu.addressbook.data.person;

/**
 * Represents a Person's unit address in the address book.
 * Guarantees: immutable;
 */

public class Unit {
    //public final String value;
    private boolean isPrivate;
    public final String value;

    public Unit(String unitAddress, boolean isPrivate) {
        String trimmedUnitAddress = unitAddress.trim();
        this.isPrivate = isPrivate;
        this.value = trimmedUnitAddress;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && this.value.equals(((Unit) other).value)); // state check
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}