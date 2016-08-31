package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, some unit, some postalcode";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = 
    		"Person addresses must be in the format: 'block, street, unit, postalcode'";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public static final int ADDRESS_BLOCK_INDEX = 0;
    public static final int ADDRESS_STREET_INDEX = 1;
    public static final int ADDRESS_UNIT_INDEX = 2;
    public static final int ADDRESS_POSTAL_CODE_INDEX = 3;
    
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    private boolean isPrivate;

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
        String[] addressParts = splitAddress(address);
        this.block = new Block(addressParts[ADDRESS_BLOCK_INDEX]);
        this.street = new Street(addressParts[ADDRESS_STREET_INDEX]);
        this.unit = new Unit(addressParts[ADDRESS_UNIT_INDEX]);
        this.postalCode = new PostalCode(addressParts[ADDRESS_POSTAL_CODE_INDEX]);
    }

    private static String[] splitAddress(String address) {
		String[] addressParts = address.split(",");
		return addressParts;
	}

	/**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX)
        		&& (splitAddress(test).length == 4);
    }

    @Override
    public String toString() {
        return this.block.toString() + "," + this.street.toString() + "," + this.unit.toString()
        			+ "," + this.postalCode.toString();
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
}