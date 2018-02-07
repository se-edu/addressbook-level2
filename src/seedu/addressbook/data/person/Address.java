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
        createAddress(trimmedAddress);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {  return formAddress(); }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && formAddress().equals(((Address) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return formAddress().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    /**
     * Fills in the attributes of the Address Class.
     * @param trimmedAddress
     */
    private void createAddress(String trimmedAddress){
        String[] items = trimmedAddress.split(", ");

        if (items.length >= 1)
        this.block = new Block(items[0]);
        if (items.length >= 2)
        this.street = new Street(items[1]);
        if (items.length >= 3)
        this.unit = new Unit(items[2]);
        if (items.length == 4)
        this.postalCode = new PostalCode(items[3]);
    }

    /**
     * Extracts from the attributes of the Address class to form a string
     * @return address in String type
     */
    private String formAddress(){
        StringBuffer str = new StringBuffer();

        if (block != null)
        str.append(block.getBlock());

        if (street != null) {
            str.append(", ");
            str.append(street.getStreet());
        }
        if (unit != null) {
            str.append(", ");
            str.append(unit.getUnit());
        }
        if (postalCode != null) {
            str.append(", ");
            str.append(postalCode.getCode());
        }

        return str.toString();
    }

    /**
     * Represents the block portion in an address
     */
    private class Block{
        private String name;

        public Block(String input){
            name = input;
        }

        public String getBlock(){return name;}
    }

    /**
     * Represents the street portion in an address
     */
    private class Street{
        private String name;

        public Street(String input){
            name = input;
        }

        public String getStreet(){return name;}
    }

    /**
     * Represents the unit portion in an address
     */
    private class Unit{
        private String number;

        public Unit(String input){
            number = input;
        }

        public String getUnit(){return number;}
    }

    /**
     * Represents the postal code portion in an address
     */
    private class PostalCode{
        private String code;

        public PostalCode(String input){
            code = input;
        }

        public String getCode(){return code;}
    }
}
