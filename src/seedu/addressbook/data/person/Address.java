package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.address.*;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should contain four parts " +
            "and separated by comma(,): Block, Street, Unit, PostalCode";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";


    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        String[] fullAddress = trimmedAddress.split(",",-1);
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress, fullAddress.length)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        if(fullAddress.length != 0){
            this.value = trimmedAddress;
            this.block = new Block(fullAddress[0]);
            this.street = new Street(fullAddress[1]);
            this.unit = new Unit(fullAddress[2]);
            this.postalCode = new PostalCode(fullAddress[3]);
        }else{
            this.value = trimmedAddress;
        }
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test, int size) {

        return test.matches(ADDRESS_VALIDATION_REGEX) && (size == 4);
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
