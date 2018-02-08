package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses is entered in the following format "
                                                           + "a/BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";
    public static final String ADDRESS_SPLIT_DELIMITER = ",";

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    private static final int BLOCK_INDEX = 0;
    private static final int STREET_INDEX = 1;
    private static final int UNIT_INDEX = 2;
    private static final int POSTALCODE_INDEX = 3;

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
        String[] splitAddressArray = trimmedAddress.split(ADDRESS_SPLIT_DELIMITER);
        this.block = new Block(splitAddressArray[BLOCK_INDEX]);
        this.street = new Street(splitAddressArray[STREET_INDEX]);
        this.unit = new Unit(splitAddressArray[UNIT_INDEX]);
        this.postalCode = new PostalCode(splitAddressArray[POSTALCODE_INDEX]);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        String fullAddress = block + ADDRESS_SPLIT_DELIMITER + " " + street + ADDRESS_SPLIT_DELIMITER + " "
                           + unit + ADDRESS_SPLIT_DELIMITER + " " + postalCode;
        return fullAddress;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
