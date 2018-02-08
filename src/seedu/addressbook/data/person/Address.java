package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;




/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String FORMAT = "BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses has to be entered in the following format: " + FORMAT;

    public final String value;
    private boolean isPrivate;

    private static Block block;
    private static Street street;
    private static Unit unit;
    private static PostalCode postalCode;

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

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String trimmedAddress) {
        String[] components = trimmedAddress.split( ",\\s*" );
        if (components.length != 4) return false;
        else {
            int count = 0;
            for ( String component : components ) {
                switch(count) {
                    case 0 :
                        block = new Block(component);
                        break;

                    case 1 :
                        street = new Street(component);
                        break;

                    case 2 :
                        unit = new Unit(component);
                        break;

                    case 3 :
                        postalCode = new PostalCode(component);
                        break;
                }
                count++;
            }
        }
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

