package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.ArrayList;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses has to be in 'a/BLOCK, STREET, UNIT, POSTAL_CODE' format. Include 'NIL' for non-existent values";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final int blockIndex = 0;
    public static final int streetIndex = 1;
    public static final int unitIndex = 2;
    public static final int postalcodeIndex = 3;

    private boolean isPrivate;
    public String value;
    public Block block;
    public Street street;
    public Unit unit;
    public PostalCode postal_code;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        String[] addressArray = trimmedAddress.split(",");
        block = new Block(addressArray[blockIndex].trim());
        street = new Street(addressArray[streetIndex].trim());
        unit = new Unit(addressArray[unitIndex].trim());
        postal_code = new PostalCode(addressArray[postalcodeIndex].trim());
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = trimmedAddress;
    }

    public Block getBlock(){
        return block;
    }

    public Street getStreet(){
        return street;
    }

    public Unit getUnit(){
        return unit;
    }

    public PostalCode getPostalCode(){
        return postal_code;
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

        //return block.isSameBlock(((Address) other).getBlock()) || street.isSameStreet(((Address) other).getStreet())
        // || unit.isSameUnit(((Address) other).getUnit()) ||   postal_code.isSamePostalCode((Address) other).getPostalCode())  ;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}

class Block {
    private String blockname;

    public Block(String name){
        blockname = name;
    }

    public String getBlockName(){
        return blockname;
    }

    public boolean isSameBlock(Block other){
        return blockname.trim().equals(other.getBlockName().trim());
    }
}

class Street {
    private String streetname;

    public Street(String name){
        streetname = name;
    }

    public String getStreetName(){
        return streetname;
    }
}

class Unit {
    private String unitno;

    public Unit(String number){
        unitno = number;
    }

    public String getUnitNo(){
        return unitno;
    }
}

class PostalCode{
    private String postalcode;

    public PostalCode(String code){
        postalcode = code;
    }

    public String getPostalCode(){
        return postalcode;
    }
}