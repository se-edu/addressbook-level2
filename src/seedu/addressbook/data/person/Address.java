package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, #11-123, S123456";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Address must be in <BLOCK>, <STREET>, <UNIT>, <POSTAL CODE>";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final String ADDRESS_SEPARATING_REGEX = ", ";

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    
    public final String value;
    private boolean isPrivate;

    public Block getBlock() {
    	return block;
    }
    
    public Street getStreet() {
    	return street;
    }
    
    public Unit getUnit() {
    	return unit;
    }
    
    public PostalCode getPostalCode() {
    	return postalCode;
    }
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
        this.value = trimmedAddress;
        String[] splitAddress;
        splitAddress = trimmedAddress.split(ADDRESS_SEPARATING_REGEX);
        this.block = new Block(splitAddress[0]);
        this.street = new Street(splitAddress[1]);
        this.unit = new Unit(splitAddress[2]);
        this.postalCode = new PostalCode(splitAddress[3]);
        }

    /**
     * Returns true if a given string is a valid person email.
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
                || (other instanceof Address // instance of handles nulls
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
