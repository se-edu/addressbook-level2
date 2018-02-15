package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Objects;

/**
 * Represents a Person's address in the address book.
 */
public class Address {
    private static final String ADDRESS_SPLIT_REGEX = ",";
    private static final String SEPARATOR = ", ";
    private static final int BLOCK_INDEX = 0;
    private static final int STREET_INDEX = 1;
    private static final int UNIT_INDEX = 2;
    private static final int POSTALCODE_INDEX = 3;

    public static final String EXAMPLE = "123, some street, #01-01, 123456";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format"
            + "'BLOCK, STREET, UNIT, POSTAL CODE'";
    public static final String ADDRESS_VALIDATION_REGEX = ".+?(?=,),.+?(?=,),.+?(?=,),.+";

    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;
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
        String[] splitedAddress = trimmedAddress.split(ADDRESS_VALIDATION_REGEX);
        this.block = new Block(splitedAddress[BLOCK_INDEX], isPrivate);
        this.street = new Street(splitedAddress[STREET_INDEX], isPrivate);
        this.unit = new Unit(splitedAddress[UNIT_INDEX], isPrivate);
        this.postalCode = new PostalCode(splitedAddress[POSTALCODE_INDEX], isPrivate);
        this.value = block.toString() + SEPARATOR + street.toString() + SEPARATOR + unit.toString()
                        + SEPARATOR + postalCode;
        this.isPrivate = isPrivate;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

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
