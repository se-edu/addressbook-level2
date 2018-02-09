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
    public Block addressBlock;
    public Street addressStreet;
    public Unit addressUnit;
    public PostalCode addressPostalCode;

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(Block b, Street s, Unit u, PostalCode p, boolean isPrivate) throws IllegalValueException {
        addressBlock = b;
        addressStreet = s;
        addressUnit = u;
        addressPostalCode = p;

        this.isPrivate = isPrivate;
        if (!isValidAddress(addressBlock, addressStreet, addressUnit, addressPostalCode)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = trimmedAddress;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(Block b, Street s, Unit u, PostalCode p) {
        //return test.matches(ADDRESS_VALIDATION_REGEX); change this to check if everything has a value
        return true;
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

public class Block{
    public String value;

    public Block(String b){
        value = b;
    }
}

public class Street{
    public String value;

    public Street(String s){
        value = s;
    }
}

public class Unit{
    public String value;

    public Street(String u){
        value = u;
    }
}

public class PostalCode{
    public String value;

    public Street(String p){
        value = p;
    }
}