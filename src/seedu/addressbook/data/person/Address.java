package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final int BLOCK_INDEX = 0;
    public static final int STREET_INDEX = 1;
    public static final int UNIT_INDEX = 2;
    public static final int POSTAL_CODE_INDEX = 3;

    public static final String TO_ADDRESS_FORMAT = "%s, %s, %s, %s";


    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS =
            "Person addresses should be in this format <BLOCK, STREET, UNIT, POSTAL_CODE>";
    public static final String ADDRESS_VALIDATION_REGEX = "[\\d ]+, [\\w ]+, [\\w \\W ]+, [\\d]+";

    public Block blockNumber;
    public Street streetName;
    public Unit unitNumber;
    public PostalCode postalCode;

    public final String value;
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
        this.value = trimmedAddress;
        String[] addressFields =  extractIndividualElements(trimmedAddress);
        this.blockNumber = new Block(addressFields[BLOCK_INDEX]);
        this.streetName = new Street(addressFields[STREET_INDEX]);
        this.unitNumber = new Unit(addressFields[UNIT_INDEX]);
        this.postalCode = new PostalCode(addressFields[POSTAL_CODE_INDEX]);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    private String[] extractIndividualElements(String trimmedAddress){
        String[] splitString = trimmedAddress.split(", ");

        return splitString;
    }

    @Override
    public String toString() {
        return String.format(TO_ADDRESS_FORMAT
                ,blockNumber.getBlockNumber()
                ,streetName.getStreetName()
                ,unitNumber.getUnitNumber()
                ,postalCode.getPostalCode());
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
