package seedu.addressbook.data.address;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "Block 123, some street, #01-01, S123456";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    private static final String ADDRESS_DELIMITER = ", ";
    private static final int NUM_PARTS = 4;

    private static final int BLOCK_INDEX = 0;
    private static final int STREET_INDEX = 1;
    private static final int UNIT_INDEX = 2;
    private static final int POSTAL_CODE_INDEX = 3;

    public final Block block;
    public final Street street;
    public final Unit unit;
    public final PostalCode postalCode;
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
        String[] addressParts = trimmedAddress.split(ADDRESS_DELIMITER);

        String blockInput = addressParts[BLOCK_INDEX];
        this.block = new Block(blockInput);

        String streetInput = addressParts[STREET_INDEX];
        this.street = new Street(streetInput);

        String unitInput = addressParts[UNIT_INDEX];
        this.unit = new Unit(unitInput);

        String postalCodeInput = addressParts[POSTAL_CODE_INDEX];
        this.postalCode = new PostalCode(postalCodeInput);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String[] addressParts = new String[NUM_PARTS];
        addressParts[BLOCK_INDEX] = this.block.toString();
        addressParts[STREET_INDEX] = this.street.toString();
        addressParts[UNIT_INDEX] = this.unit.toString();
        addressParts[POSTAL_CODE_INDEX] = this.postalCode.toString();
        return String.join(ADDRESS_DELIMITER, addressParts);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(other.toString())); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
