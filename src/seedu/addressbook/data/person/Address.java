package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "311, Clementi Ave 2, #02-25, 182938";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should be in the format: BLOCK, STREET, UNIT NUMBER, POSTAL CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";

    public String value;
    private boolean isPrivate;
	
	private Block block;
	private Street street;
	private UnitNumber unitNumber;
	private PostalCode postalCode;
	
	private final int BLOCK_INDEX = 0;
	private final int STREET_INDEX = 1;
	private final int UNIT_NUMBER_INDEX = 2;
	private final int POSTAL_CODE_INDEX = 3;
	
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
        splitAddress(trimmedAddress);
    }
	/**
	  * Split trimmedAddress into 4 classes: Block, Street, UnitNumber, PostalCode
	  */
	public void splitAddress(String trimmedAddress) {
		String[] addressDetails= trimmedAddress.split(",");
		block = new Block(addressDetails[BLOCK_INDEX].trim());
		street = new Street(addressDetails[STREET_INDEX].trim());
		unitNumber = new UnitNumber(addressDetails[UNIT_NUMBER_INDEX].trim());
		postalCode = new PostalCode(addressDetails[POSTAL_CODE_INDEX].trim());
	}
	
    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
	/**
	  * Returns address in the format: block, street, unitNumber, postalCode
	  */
    public String toString() {
        return block.toString() + ", " + street.toString() + ", " + unitNumber.toString() + ", " + postalCode.toString();
    }
	/**
	  * Getter methods to get address details (block, street, unitNumber, postalCode) separately 
	  */
	
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
