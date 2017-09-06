package seedu.addressbook.data.person.address;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public static final int ADDRESS_DATA_BLOCK_ORDER = 0;
    public static final int ADDRESS_DATA_STREET_ORDER = 1;
    public static final int ADDRESS_DATA_UNIT_ORDER = 2;

    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    public String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        String[] addressComponents = trimmedAddress.split(",");
        this.isPrivate = isPrivate;
        this.block = new Block(extractBlockFromAddressString(addressComponents[0]));
        this.street = new Street(extractStreetFromAddressString(addressComponents[1]));
        this.unit = new Unit(extractUnitFromAddressString(addressComponents[2]));
        this.postalCode = new PostalCode(extractPostalCodeFromAddressString(addressComponents[3]));
        if(!isValidAddress(trimmedAddress)){
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = trimmedAddress;
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

    public String extractBlockFromAddressString(String encoded){
        return encoded.trim();
    }

    public String extractStreetFromAddressString(String encoded){
        return encoded.trim();
    }

    public String extractUnitFromAddressString(String encoded){
        return encoded.trim();
    }

    public String extractPostalCodeFromAddressString(String encoded){
        return encoded.trim();
    }

    public static int ordinalIndexOf(String str, String substr, int n) {
        int pos = str.indexOf(substr);
        while (--n > 0 && pos != -1)
            pos = str.indexOf(substr, pos + 1);
        return pos;
    }
}
