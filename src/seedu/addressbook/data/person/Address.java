package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "a/123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should be in format of a/BLOCK, STREET, UNIT, POSTAL_CODE ";
    public static final String ADDRESS_VALIDATION_REGEX = ".+,.+,.+,.+";


    private static Block block=null;
    private static Street street=null;
    private static Unit unit=null;
    private static PostalCode postalCode=null;

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
    }

    private void seperateAddress(String address){
        String[] eachInfo = address.split(",");
        block = new Block(eachInfo[0].trim());
        street = new Street(eachInfo[1].trim());
        unit = new Unit(eachInfo[2].trim());
        postalCode = new PostalCode(eachInfo[3].trim());
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public boolean isValidAddress(String test) {
        Boolean isValid = test.matches(ADDRESS_VALIDATION_REGEX);
        seperateAddress(test);
        isValid = test.matches(ADDRESS_VALIDATION_REGEX)
                  &&block.isValid()
                  &&street.isValid()
                  &&unit.isValid()
                  &&postalCode.isValid();
        return isValid;
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
