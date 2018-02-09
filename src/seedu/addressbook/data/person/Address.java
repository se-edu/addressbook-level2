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
    private Block b1;
    private Unit u1;
    private Street s1;
    private PostalCode p1;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String[] splitaddress = address.split("\\s");
        b1.value = splitaddress[0];
        u1.value = splitaddress[1];
        s1.value = splitaddress[2];
        p1.value = splitaddress[3];
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        b1.isPrivate = isPrivate;
        u1.isPrivate = isPrivate;
        s1.isPrivate = isPrivate;
        p1.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
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

    public String getBlock(){ return b1.value;}

    public String getUnit(){ return u1.value;}

    public String getStreet(){ return s1.value;}

    public String getPostalCode(){ return p1.value;}
}
