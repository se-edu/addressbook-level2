package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, some unit, some postal code";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must be in format of <BLOCK, STREET, UNIT, POSTAL CODE>";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final String ADDRESS_SPLIT_REGEX = ",";

    public static final int ADDRESS_BLOCK_INDEX = 0;
    public static final int ADDRESS_STREET_INDEX = 1;
    public static final int ADDRESS_UNIT_INDEX = 2;
    public static final int ADDRESS_POSTAL_CODE_INDEX = 3;
    public static final int ADDRESS_FIELD_NUMBER = 4;

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    private boolean isPrivate;
    public String value;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        String[] splitAddress = trimmedAddress.split(ADDRESS_SPLIT_REGEX);

        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        initializeAddress(splitAddress);
    }

    /**
     * Initializes the field objects block, street, unit and postal code
     * @param splitAddress
     */
    private void initializeAddress(String[] splitAddress) {
        block = new Block(splitAddress[ADDRESS_BLOCK_INDEX].replace(ADDRESS_SPLIT_REGEX, "").trim());
        street = new Street(splitAddress[ADDRESS_STREET_INDEX].replace(ADDRESS_SPLIT_REGEX, "").trim());
        unit = new Unit(splitAddress[ADDRESS_UNIT_INDEX].replace(ADDRESS_SPLIT_REGEX, "").trim());
        postalCode = new PostalCode(splitAddress[ADDRESS_POSTAL_CODE_INDEX].replace(ADDRESS_SPLIT_REGEX, "").trim());
        value = this.toString();
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX) && test.split(ADDRESS_SPLIT_REGEX).length == ADDRESS_FIELD_NUMBER;
    }

    @Override
    public String toString() {
        return block.getBlock() + ADDRESS_SPLIT_REGEX + " "
                + street.getStreet() + ADDRESS_SPLIT_REGEX + " "
                + unit.getUnit() + ADDRESS_SPLIT_REGEX + " "
                + postalCode.getPostalCode();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
