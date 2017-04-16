package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "a/12";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must be in this format: a/BLOCK, STREET, UNIT, POSTAL CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final String ADDRESS_DELIMITER = ",";
    

    private final String value;
    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;
    private boolean isPrivate;

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
        String[] splitAddr = trimmedAddress.split(ADDRESS_DELIMITER);
        
        block = new Block(splitAddr[0]);
        street = new Street(splitAddr[1]);
        unit = new Unit(splitAddr[2]);
        postalCode = new PostalCode(splitAddr[3]);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        String[] splitAddr = test.split(ADDRESS_DELIMITER);
        if (splitAddr.length == 4)
            return true;
        else
            return false;
    }

    @Override
    public String toString() {
        return block + ", " + street + ", " + unit + ", " + postalCode;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
                && this.block.equals(((Address) other).block));
                && this.street.equals(((Address) other).street));
                && this.unit.equals(((Address) other).unit));
                && this.postalCode.equals(((Address) other).postalCode));
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
