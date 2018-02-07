package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, #02-123, 654123";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Please input in the format: Block, Street, Unit, Postal code";
    public static final String INVALID_UNIT = "Unit is invalid. Example unit: #02-123";
    public static final String ADDRESS_REGEX = ",";
    public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";
    public static final String UNIT_VALIDATION_REGEX = "^#[0-Z]{1,3}-[0-Z]{1,5}$";
    public static final int BLOCK_INDEX = 0;
    public static final int STREET_INDEX = 1;
    public static final int UNIT_INDEX = 2;
    public static final int POSTAL_CODE_INDEX = 3;

    public final BlockNumber block;
    public final Street street;
    public final Unit unit;
    public final PostalCode postalCode;

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;

        this.value = address.trim();
        if (!isValidAddress(value)){
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }

        String[] addressParts = address.split(ADDRESS_REGEX);
        if (!isValidUnit(addressParts[UNIT_INDEX].trim())) {
            throw new IllegalValueException(INVALID_UNIT);
        }
        block = new BlockNumber(addressParts[BLOCK_INDEX].trim());
        street = new Street(addressParts[STREET_INDEX]);
        unit = new Unit(addressParts[UNIT_INDEX].trim());
        postalCode = new PostalCode(addressParts[POSTAL_CODE_INDEX].trim());
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid unit number.
     */
    public static boolean isValidUnit(String test){
        return test.matches(UNIT_VALIDATION_REGEX);
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

class BlockNumber{
    public final String blockNo;

    public BlockNumber(String blockNo){
        this.blockNo = blockNo;
    }
}

class Street{
    public final String streetName;

    public Street(String streetName){
        this.streetName = streetName;
    }
}

class Unit{
    public final String unitNo;

    public Unit(String unitNo) {
        this.unitNo = unitNo;
    }
}

class PostalCode{
    public final String postalCodeNo;

    public PostalCode(String postalCodeNo){
        this.postalCodeNo = postalCodeNo;
    }
}