package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must be in this format: a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = "(\\d+)(,)(.+)(,)(#| #)(\\d+)(-)(\\d+)(,)( \\d+|\\d+)";
    private static final int BLOCK_INDEX = 0;
    private static final int STREET_INDEX = 1;
    private static final int UNIT_INDEX = 2;
    private static final int POSTAL_CODE_INDEX = 3;
    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;

    //public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] addressArray = splitAddressIntoBlockStreetUnitPostalCode(address);
        this.block = new Block(addressArray[BLOCK_INDEX]);
        this.street = new Street(addressArray[STREET_INDEX]);
        this.unit = new Unit(addressArray[UNIT_INDEX]);
        this.postalCode = new PostalCode(addressArray[POSTAL_CODE_INDEX]);
}
    

    /**
     * Splits address into block, street, unit and postal code and returns them in an array
     * @param address
     * @return an array containing the split up address
     */
    private String[] splitAddressIntoBlockStreetUnitPostalCode(String address) {
        String[] addressArray = address.split(",");
        for (String element: addressArray) {
            element.trim();
        }
        return addressArray;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String address) {
        return address.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return block.toString() + ", " + street.toString() + ", " + unit.toString() + ", " + postalCode.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return block.hashCode() + street.hashCode() + unit.hashCode() + postalCode.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}