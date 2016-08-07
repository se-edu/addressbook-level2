package seedu.addressbook.model.person;

import seedu.addressbook.Utils;
import seedu.addressbook.model.InvalidDataException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address extends ContactDetail {

    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addressescan be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String address;

    /**
     * Validates given address.
     *
     * @throws InvalidDataException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws InvalidDataException {
        super(isPrivate);
        Utils.assertNotNull(address);
        if (!isValidAddress(address)) {
            throw new InvalidDataException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.address = address;
    }

    /**
     * Checks if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return address;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.address.equals(((Address) other).address)); // state check
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }

}