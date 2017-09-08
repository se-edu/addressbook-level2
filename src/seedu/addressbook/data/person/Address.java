package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses are in the format: a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final String SPLIT_BY_COMMA = ", ";

    private boolean isPrivate;

    private String[] splitValue;
    private Block block = new Block();
    private Street street = new Street();
    private Unit unit = new Unit();
    private PostalCode postalCode = new PostalCode();

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

        splitValue = trimmedAddress.split(SPLIT_BY_COMMA);

        if (splitValue.length > 0) {
            block.setBlockNumber(splitValue[0]);
        }
        if (splitValue.length > 1) {
            street.setStreetNumber(splitValue[1]);
        }
        if (splitValue.length  > 2) {
            unit.setUnitNumber(splitValue[2]);
        }
        if (splitValue.length  > 3) {
            postalCode.setPostalCodeNumber(splitValue[3]);
        }

    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String result = "";
        if (block.getBlockNumber() != "") {
            result = result + block.getBlockNumber();
        }
        if (street.getStreetNumber() != "") {
            result = result + SPLIT_BY_COMMA + street.getStreetNumber();
        }
        if (unit.getUnitNumber() != "") {
            result = result + SPLIT_BY_COMMA + unit.getUnitNumber();
        }
        if (postalCode.getPostalCodeNumber() != "") {
            result = result + SPLIT_BY_COMMA + postalCode.getPostalCodeNumber();
        }

        return result;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
