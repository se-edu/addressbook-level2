package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in data constructors.
 */
public class Address {

    public static final String EXAMPLE = 
            Block.EXAMPLE + ", " +
            Street.EXAMPLE + ", " +
            Unit.EXAMPLE + ", " +
            PostalCode.EXAMPLE;
    
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = 
            "Person addresses should follow the following format: \n" +
            Block.MESSAGE_BLOCK_CONSTRAINTS + "\n" +
            Street.MESSAGE_STREET_CONSTRAINTS + "\n" +
            Unit.MESSAGE_UNIT_CONSTRAINTS + "\n" +
            PostalCode.MESSAGE_POSTAL_CODE_CONSTRAINTS;

    private static final int ADDRESS_BLOCK_INDEX = 0;
    private static final int ADDRESS_STREET_INDEX = 1;
    private static final int ADDRESS_UNIT_INDEX = 2;
    private static final int ADDRESS_POSTAL_CODE_INDEX = 3;
    
    public final String value;
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
        
        try {
            String trimmedAddress = address.trim();
            
            String[] addressData = trimmedAddress.split(", ");
            
            block = new Block(addressData[ADDRESS_BLOCK_INDEX]);
            street = new Street(addressData[ADDRESS_STREET_INDEX]);
            unit = new Unit(addressData[ADDRESS_UNIT_INDEX]);
            postalCode = new PostalCode(addressData[ADDRESS_POSTAL_CODE_INDEX]);
            
            this.isPrivate = isPrivate;
            this.value = trimmedAddress;
                     
        } catch (IllegalValueException error) {
            throw new IllegalValueException(error.getMessage());
        }
        
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
