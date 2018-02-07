package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String[])}
 */
public class Address {

    public static final String EXAMPLE = "311, Clementi Ave 2, #02-25, 424311";
    private static final String MESSAGE_ADDRESS_CONSTRAINTS = "Address must be the form: "
            + "block number, street name, unit number, postal code";
    private static final String ADDRESS_DELIMITER = ", ";

    private boolean isPrivate;
    private Block blkNo;
    private Street streetName;
    private Unit unitNo;
    private PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String[] splitAddress = address.split(ADDRESS_DELIMITER);
        if (splitAddress.length != 4){
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        if (isValidAddress(splitAddress)) {
            this.isPrivate = isPrivate;
            blkNo = new Block(splitAddress[0]);
            streetName = new Street(splitAddress[1]);
            unitNo = new Unit(splitAddress[2]);
            postalCode = new PostalCode(splitAddress[3]);
        }
    }

    /**
     * Returns true if a given string is a valid person address.
     * @param test
     */
    private static boolean isValidAddress(String[] test) throws IllegalValueException{
        return Block.isValidBlock(test[0]) && Street.isValidStreet(test[1])
                && Unit.isValidUnit(test[2]) && PostalCode.isValidPostalCode(test[3]);
    }

    @Override
    public String toString() {
        return blkNo.toString() + ", " + streetName.toString()
                + ", " + unitNo.toString() + ", " + postalCode.toString();
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
