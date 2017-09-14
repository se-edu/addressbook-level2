package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String value;
    private boolean isPrivate;
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        System.out.println("Untrimmed Address: " + address);
        String trimmedAddress = address.trim();
        System.out.println("I trimmed it: " + trimmedAddress);
        ArrayList<String> addressInfo = processAddress(trimmedAddress);
        System.out.println(addressInfo.toString());
        this.block = new Block(addressInfo.get(0));
        this.street = new Street(addressInfo.get(1));
        this.unit = new Unit(addressInfo.get(2));
        this.postalCode = new PostalCode(addressInfo.get(3));

        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = trimmedAddress;
    }

    public ArrayList<String> processAddress(String trimmedAddress) {
        ArrayList<String> output = new ArrayList<String>(Arrays.asList(trimmedAddress.split(",\\s+")));
        return output;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
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
