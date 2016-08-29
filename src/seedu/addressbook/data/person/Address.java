package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.address.Block;
import seedu.addressbook.data.person.address.PostalCode;
import seedu.addressbook.data.person.address.Street;
import seedu.addressbook.data.person.address.Unit;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, some unit number, some postal code";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should be in a/BLOCK, STREET, UNIT, POSTAL CODE.";
    public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";

    private static final int BLOCK_INDEX_IN_ADDRESS = 0;
    private static final int STREET_INDEX_IN_ADDRESS = 1;
    private static final int UNIT_INDEX_IN_ADDRESS = 2;
    private static final int POSTALCODE_INDEX_IN_ADDRESS = 3;
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
        this.isPrivate = isPrivate;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        String[] addressParams = address.split(",");
        this.block = new Block(addressParams[BLOCK_INDEX_IN_ADDRESS].trim());
        this.street = new Street(addressParams[STREET_INDEX_IN_ADDRESS].trim());
        this.unit = new Unit(addressParams[UNIT_INDEX_IN_ADDRESS].trim());
        this.postalCode = new PostalCode(addressParams[POSTALCODE_INDEX_IN_ADDRESS].trim());
        this.value = address;
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