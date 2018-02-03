package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable
 */
public class Address {

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS =
            "Person addresses should be entered by block number, street, unit and followed by postal code"
                    + "separated by ','";
    public static final String SEPARATOR_COMMA = ", ";
    // TODO: add ADDRESS_VALIDATION_REGEX for better input validation

    public final String value;
    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;
    private boolean isPrivate;

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

        String[] components = address.split(SEPARATOR_COMMA);
        block = new Block(components[0]);
        street = new Street(components[1]);
        unit = new Unit(components[2]);
        postalCode = new PostalCode((components[3]));
        value = this.getFullAddressValue();
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        // TODO: A better validation for each component
        return test.split(",").length == 4; // valid if contains block number, street, unit and postal code
    }

    public String getFullAddressValue() {
        return block.toString() + SEPARATOR_COMMA + street.toString() + SEPARATOR_COMMA + unit.toString()
                + SEPARATOR_COMMA + postalCode.toString();
    }

    public Block getBlock() { return block; }

    public Street getStreet() { return street; }

    public Unit getUnit() { return unit; }

    public PostalCode getPostalCode() { return postalCode; }

    public boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public String toString() {
        return block.toString() + SEPARATOR_COMMA + street.toString() + SEPARATOR_COMMA + unit.toString()
                + SEPARATOR_COMMA + postalCode.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.block.toString().equals(((Address) other).getBlock().toString())
                && this.street.toString().equals(((Address) other).getStreet().toString()))
                && this.unit.toString().equals((((Address) other).getUnit().toString()))
                && this.postalCode.toString().equals(((Address) other).getPostalCode().toString()); // state check
    }

    @Override
    public int hashCode() {
        String fullAddress = block.toString() + SEPARATOR_COMMA + street.toString() + SEPARATOR_COMMA + unit.toString()
                + SEPARATOR_COMMA + postalCode.toString();
        return fullAddress.hashCode();
    }
}
