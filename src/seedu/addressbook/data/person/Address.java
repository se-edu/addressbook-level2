package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

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
        /*
        This will give indexOutOfBound when input does not
        follow format of "a/BLOCK, STREET, UNIT, POSTAL_CODE"
        */
        String[] addressComponentArray = trimmedAddress.split(", ");
        this.block = new Block(addressComponentArray[0]);
        this.street = new Street(addressComponentArray[1]);
        this.unit = new Unit(addressComponentArray[2]);
        this.postalCode = new PostalCode(addressComponentArray[3]);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.block.getBlockValue() + ", " + this.street.getStreetValue()
                + ", " + this.unit.getUnitValue() + ", " + this.postalCode.getPostalCodeValue();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }


    /**
     * A bunch of classes Address class relies on
     */
    private class Block{

        private String value;

        public Block(String block){
            this.value = block;
        }

        public String getBlockValue(){return this.value;}
    }

    private class Street{

        private String value;

        public Street(String street){
            this.value = street;
        }

        public String getStreetValue(){return this.value;}
    }

    private class Unit{

        private String value;

        public Unit(String unit){
            this.value = unit;
        }

        public String getUnitValue(){return this.value;}
    }

    private class PostalCode{

        private String value;

        public PostalCode(String postalCode){
            this.value = postalCode;
        }

        public String getPostalCodeValue(){return this.value;}
    }
}

