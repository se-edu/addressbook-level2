package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "a/123, Clementi Ave 3, #12-34, 231534";
    // public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    //public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person's address only can be in this fo format: a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+, .+, .+, .+";


    public final String value;
    private boolean isPrivate;
    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;

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
        //this.value = trimmedAddress;
        String[] splitAddress = trimmedAddress.split(", ", 4);
        this.block = new Block(splitAddress[0]);
        this.street = new Street(splitAddress[1]);
        this.unit = new Unit(splitAddress[2]);
        this.postalCode = new PostalCode(splitAddress[3]);
        this.value = this.block + ", " + this.street + ", " + this.unit + ", " + this.postalCode;
    }


    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
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

    class PostalCode {
        public String postalCode;

        public PostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        @Override
        public String toString() {
            return postalCode;
        }
    }

    class Block {
        public String blkName;

        public Block(String blockName) {
            this.blkName = blockName;
        }

        @Override
        public String toString() {
            return blkName;
        }
    }

    class Unit {
        public String unitNo;

        public Unit(String unitNumber) {
            this.unitNo = unitNumber;
        }

        @Override
        public String toString() {
            return unitNo;
        }
    }

    class Street {
        public String streetNme;

        public Street(String streetName) {
            this.streetNme = streetName;
        }

        @Override
        public String toString() {
            return streetNme;
        }
    }


}
