package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable
 */
public class Address {

    private static final int INDEX_BLOCK = 0;
    private static final int INDEX_STREET = 1;
    private static final int INDEX_UNIT = 2;
    private static final int INDEX_POSTAL_CODE = 3;

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS =
            "Person addresses should be entered by block number, street, unit and followed by postal code"
                    + "separated by ','";
    public static final String ADDRESS_FORMAT = "BLOCK, STREET, UNIT, POSTAL_CODE";
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
        block = new Block(initialiseBlockValue(components));
        street = new Street(initialiseStreetValue(components));
        unit = new Unit(initialiseUnitValue(components));
        postalCode = new PostalCode(initialisePostalCodeValue(components));

        value = this.getFullAddressValue();
    }

    /**
     * Returns the initialised block value from the String array of the Address split into the components.
     */
    private String initialiseBlockValue(String[] addressComponents) {
        return addressComponents.length < 1? "" : addressComponents[INDEX_BLOCK];
    }

    /**
     * Returns the initialised street value from the String array of the Address split into the components.
     */
    private String initialiseStreetValue(String[] addressComponents) {
        return addressComponents.length < 2? "" : addressComponents[INDEX_STREET];
    }

    /**
     * Returns the initialised unit value from the String array of the Address split into the components.
     */
    private String initialiseUnitValue(String[] addressComponents) {
        return addressComponents.length < 3? "" : addressComponents[INDEX_UNIT];
    }

    /**
     * Returns the initialised postal code value from the String array of the Address split into the components.
     */
    private String initialisePostalCodeValue(String[] addressComponents) {
        return addressComponents.length < 4 ? "" : addressComponents[INDEX_POSTAL_CODE];
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    private static boolean isValidAddress(String test) {
        // TODO: A better validation for each component
        return test.split(",").length > 0; // has at least one component
    }

    private String getFullAddressValue() {
        String fullAddress = "";
        if (!block.getValue().equals("")) {
            fullAddress += block.getValue();
        }
        if (!street.getValue().equals("")) {
            fullAddress += SEPARATOR_COMMA + street.getValue();
        }
        if (!unit.getValue().equals("")) {
            fullAddress += SEPARATOR_COMMA + unit.getValue();
        }
        if (!postalCode.getValue().equals("")) {
            fullAddress += SEPARATOR_COMMA + postalCode.getValue();
        }

        return fullAddress;
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
        return getFullAddressValue();
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
