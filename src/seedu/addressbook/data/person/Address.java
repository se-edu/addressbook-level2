package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must be BLOCK, STREET, UNIT, POSTALCODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    public static final String DIVIDER = "; ";

    public final Block block;
    public final Street street;
    public final Unit unit;
    public final PostalCode postalCode;
    private boolean isPrivate;
    public final String value;

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
        String[] addressInfo = trimmedAddress.split(", ");
        this.block = new Block(addressInfo[0]);
        this.street = new Street(addressInfo[1]);
        this.unit = new Unit(addressInfo[2]);
        this.postalCode = new PostalCode(addressInfo[3]);
        this.value = this.toString();
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        if(!test.matches(ADDRESS_VALIDATION_REGEX)){
            return false;
        }else if(test.split(", ").length != 4){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return block.toString() + DIVIDER
                + street.toString() + DIVIDER
                + unit.toString() + DIVIDER
                + postalCode.toString();
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
