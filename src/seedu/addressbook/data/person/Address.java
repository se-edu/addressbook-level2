package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_FORMAT = "a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final int ADDRESS_COMPONENTS = 4;
    public static final String token = ",";

    public static final int blockIndex = 0;
    public static final int streetIndex = 1;
    public static final int unitIndex = 2;
    public static final int postalIndex = 3;

    public final String value;
    public final Block block = new Block();
    public final Street street = new Street();
    public final Unit unit = new Unit();
    public final Postal postal = new Postal();

    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate)  {
        String trimmedAddress = address.trim();
        this.value = trimmedAddress;

        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
           System.out.println("Address structure is not valid");
        }
        else {

            String[] splits = splitAddress(value);


            block.setBlockNumber(splits[blockIndex].trim());
            street.setStreet(splits[streetIndex].trim());
            unit.setUnitNumber(splits[unitIndex].trim());
            postal.setPostal(splits[postalIndex].trim());

        }


    }


    /**
     *Splitting address to various components
     */

    public String[] splitAddress(String address){

        String[] splits = address.split(token);

        return splits;
    }



    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        String[] components = test.split(token);
        if(components.length == 4)
            return true;
        else
            return false;
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
}

class Block {
    private String blockNumber;

    public Block(){

    }

    public Block(String number) {
        this.blockNumber = blockNumber;
    }

    public String getBlockNumber(){
        return  this.blockNumber;
    }

    public void setBlockNumber(String number){
        this.blockNumber = number;
    }
}

class Unit{
    private String unitNumber;

    public Unit(){

    }
    public Unit(String number){
        this.unitNumber = number;
    }

    public String getUnitNumber() {
        return unitNumber;
    }

    public void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }
}

class Street{
    private String street;

    public Street(){

    }
    public Street(String street){
        this.street = street;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}

class Postal {
    private String postal;

    public Postal(){

    }
    public Postal(String postal){
        this.postal = postal;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }
}