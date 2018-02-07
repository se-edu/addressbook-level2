package seedu.addressbook.data.person;

import jdk.nashorn.internal.ir.Block;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, #02-123, 654123";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Please input in the format: Block, Street, Unit, Postal code";
    public static final String INVALID_UNIT = "Unit is invalid. Example unit: #02-123";
    public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";
    public static final String UNIT_VALIDATION_REGEX = "^#[0-Z]{1,3}-[0-Z]{1,5}$";

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
        this.value = address.trim();

        String[] addressParts = address.split(",");
        if (addressParts.length == 4) {
            if (!isValidUnit(addressParts[2].trim())) {
                throw new IllegalValueException(INVALID_UNIT);
            }
            block = new BlockNumber(addressParts[0].trim());
            street = new Street(addressParts[1]);
            unit = new Unit(addressParts[2].trim());
            postalCode = new PostalCode(addressParts[3].trim());
        }
        else {
            block = new BlockNumber("123");
            street = new Street("Dummy Street");
            unit = new Unit("#05-123");
            postalCode = new PostalCode("2345");
        }

        this.isPrivate = isPrivate;
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