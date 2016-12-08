package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Type the person's address in the format BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    // \w any alphanumeric \D non-digit character \w any alpha-numeric \d any digit

    public final String value;
    private boolean isPrivate;
    private static String BLOCK;
    private static String STREET;
    private static String UNIT;
    private static String POSTAL_CODE;

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
        this.value = address;
        splitAddressIntoParts(address);
    }
    
    /**
     * Splits the Address into its four different parts
     */
    
    private void splitAddressIntoParts(String address){
    	String[] parts = address.split(",");
        BLOCK = parts[0];
        STREET = parts[1];
        UNIT = parts[2];
        POSTAL_CODE = parts[3];
    }
    
    /**
     * Get methods for the different components of the Address
     */
    
    public String getBlock(){
    	return BLOCK;
    }
    
    public String getStreet(){
    	return STREET;
    }
    
    public String getUnit(){
    	return UNIT;
    }
    
    public String getPostalCode(){
    	return POSTAL_CODE;
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
    
}
