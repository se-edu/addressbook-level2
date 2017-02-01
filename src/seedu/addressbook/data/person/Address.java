package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

	private Block block;
	private Street street;
	private Unit unit;
	private PostalCode postalCode;
    
	public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    //to do 
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should be like 123, Clementi Ave 3, #12-34, 231534";

    
    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
    	String trimmedAddress = address.trim();
    	String[] splittedAddress = trimmedAddress.split(",");
        this.isPrivate = isPrivate;
        if (!isValidAddress(splittedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        } else {
        	block = new Block(splittedAddress[0].trim());
        	street = new Street(splittedAddress[1].trim());
        	unit = new Unit(splittedAddress[2].trim());
        	postalCode = new PostalCode(splittedAddress[3].trim());
        	
        }
        this.value = trimmedAddress;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String[] test) {
        boolean isValidAddress = false;
        if (test.length == 4 && Block.isValid(test[0].trim()) && Street.isValid(test[1].trim()) 
        		&& Unit.isValid(test[2].trim()) && PostalCode.isValid(test[3].trim())) {
        	isValidAddress = true;
        }
        return isValidAddress;
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
