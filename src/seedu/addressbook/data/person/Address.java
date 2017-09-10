package seedu.addressbook.data.person;

import javafx.geometry.Pos;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final String DIVIDER = ", ";

    private boolean isPrivate;
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalcode;

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
        String[] addressArr = trimmedAddress.split(",");
        if (addressArr.length > 0) {
            block = new Block(addressArr[0]);
        } else {
            block = new Block("");
        }
        if (addressArr.length > 1) {
            street = new Street(addressArr[1]);
        } else {
            street = new Street("");
        }
        if (addressArr.length > 2) {
            unit = new Unit(addressArr[2]);
        } else {
            unit = new Unit("");
        }
        if (addressArr.length > 3) {
            postalcode = new PostalCode(addressArr[3]);
        } else {
            postalcode = new PostalCode("");
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
        String address = "";
        if (block.getBlock().equals("")) {
            address += block.getBlock();
        }
        if (street.getStreet().equals("")) {
            address += DIVIDER + street.getStreet();
        }
        if (unit.getUnit().equals("")) {
            address += DIVIDER + unit.getUnit();
        }
        if (postalcode.getPostalCode().equals("")) {
            address += DIVIDER + postalcode.getPostalCode();
        }
        return address;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }
    /*
    @Override
    public int hashCode() {
        return value.hashCode();
    }
    */

    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Stores Block number
     */
    private class Block {
        private String blockName;
        public Block(String blockName) {
            this.blockName = blockName.trim();
        }
        public String getBlock() {
            return blockName;
        }
    }
    /**
     * Stores Street name
     */
    private class Street {
        private String streetName;
        public Street(String streetName) {
            this.streetName = streetName.trim();
        }
        public String getStreet() {
            return streetName;
        }
    }
    /**
     * Stores Unit number
     */
    private class Unit {
        private String unitName;
        public Unit(String unitName) {
            this.unitName = unitName.trim();
        }
        public String getUnit() {
            return unitName;
        }
    }
    /**
     * Stores Postal Code
     */
    private class PostalCode {
        private String postalCodeName;
        public PostalCode(String postalCodeName) {
            this.postalCodeName = postalCodeName.trim();
        }
        public String getPostalCode() {
            return postalCodeName;
        }
    }
}
