package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

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
        setAddressElements(address);

    }

    /**
     * Creates an object for Block, Street, Unit, and PostalCode class depending on the address input
     * @param address
     */

    private void setAddressElements(String address) {
        String[] addressList = address.split(",");
        if (addressList.length == 1) {
            Block blockNumber = new Block(Integer.parseInt(addressList[0]));
        }
        if (addressList.length == 2) {
            Block blockNumber = new Block(Integer.parseInt(addressList[0]));
            Street streetName = new Street(addressList[1]);
        }
        if (addressList.length == 3) {
            Block blockNumber = new Block(Integer.parseInt(addressList[0]));
            Street streetName = new Street(addressList[1]);
            Unit unitNumber = new Unit(addressList[2]);
        }
        if (addressList.length == 4) {
            Block blockNumber = new Block(Integer.parseInt(addressList[0]));
            Street streetName = new Street(addressList[1]);
            Unit unitNumber = new Unit(addressList[2]);
            PostalCode postalCodeNumber = new PostalCode(addressList[3]);
        }
        else {
            System.out.println("Address is empty");
        }
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
