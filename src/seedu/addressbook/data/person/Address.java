package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, #00-000, 999999";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should be in the format <block>,<street>,<unit>,<postalcode>";
    public static final String ADDRESS_VALIDATION_REGEX = "(.*),(.*),(.*)-(.*),(.*)";
    public static final int ADDRESS_INDEX_BLOCK = 0;
    public static final int ADDRESS_INDEX_STREET = 1;
    public static final int ADDRESS_INDEX_UNIT = 2;
    public static final int ADDRESS_INDEX_POSTAL_CODE = 3;

    private boolean isPrivate;

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] values = address.split(",");
        this.block = new Block(values[ADDRESS_INDEX_BLOCK]);
        this.street = new Street(values[ADDRESS_INDEX_STREET]);
        this.unit = new Unit(values[ADDRESS_INDEX_UNIT]);
        this.postalCode = new PostalCode(values[ADDRESS_INDEX_POSTAL_CODE]);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {

        return this.block.toString() + ", " +
                this.street.toString() + ", " +
                this.unit.toString() + ", " +
                this.postalCode.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.block.equals(((Address) other).block)
                && this.street.equals(((Address) other).street)
                && this.unit.equals(((Address) other).unit)
                && this.postalCode.equals(((Address) other).postalCode)); // state check
    }

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 17 + block.hashCode();
        hash = hash * 31 + street.hashCode();
        hash = hash * 13 + unit.hashCode();
        hash = hash * 19 + postalCode.hashCode();
        return hash;
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
