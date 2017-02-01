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
    public static final String ADDRESS_PREFIX = "a/";

    public final String value;
    private boolean isPrivate;
    
    public final Block block;
    public final Street street;
    public final Unit unit;
    public final PostalCode postalCode;
    
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
        String copyTrimmedAddress = new String(trimmedAddress);
        String[] addressParts = copyTrimmedAddress.split(",");
        this.block = new Block(addressParts[0].trim().substring(2));
        this.street = new Street(addressParts[1].trim());
        this.unit = new Unit(addressParts[2].trim());
        this.postalCode = new PostalCode(addressParts[3].trim());
        
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        //return value;
    	return ADDRESS_PREFIX + this.block.getBlock() + ", " + this.street.getStreet()
    		+ ", " + this.unit.getUnit() + ", " + this.postalCode.getPostalCode();
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
