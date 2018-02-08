package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "<BLOCK>, <STREET>, <UNIT>, <POSTALCODE>";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must follow:" + EXAMPLE;
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    private Block block;
    private Street street;
    private Unit unit;
    private Postalcode postalCode;

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
        this.value = trimmedAddress;
        splitAddress(trimmedAddress);
        return;

    }

    private void splitAddress(String trimmedAddress) throws IllegalValueException {
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        } else {
            String address[] = trimmedAddress.split(",");
            String block;
            String street;
            String unit;
            String postalCode;
            if (!address[0].isEmpty()) {
                block = address[0];
                this.block = new Block(block);
            }
            if (!address[1].isEmpty()) {
                street = address[1];
                this.street = new Street(street);
            }
            if (!address[2].isEmpty()) {
                unit = address[2];
                this.unit = new Unit(unit);
            }
            if (!address[3].isEmpty()) {
                postalCode = address[3];
                this.postalCode = new Postalcode(postalCode);
            }

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
