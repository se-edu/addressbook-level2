package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, Clementi Ave 3, #12-34, 231534";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses should be in the following format: " +
            "BLOCK, STREET, UNIT, POSTAL_CODE";
    public static final String ADDRESS_VALIDATION_REGEX = "\\d+, [\\w\\d\\s]+, #\\d+-\\d+, \\d{6}$";

    public final Block block;
    public final Street street;
    public final Unit unit;
    public final PostalCode postalCode;

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

        String[] splitAddress = trimmedAddress.split(", ");
        block = new Block(splitAddress[0]);
        street = new Street(splitAddress[1]);
        unit = new Unit(splitAddress[2]);
        postalCode = new PostalCode(splitAddress[3]);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", block.get(), street.get(), unit.get(), postalCode.get());
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.toString().equals(((Address) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    private class Block {
        private final String _block;
        Block(String block) { _block = block; }
        String get() { return _block; }
    }

    private class Street {
        private final String _street;
        Street(String street) { _street = street; }
        String get() { return _street; }
    }

    private class Unit {
        private final String _unit;
        Unit(String unit) { _unit = unit; }
        String get() { return _unit; }
    }

    private class PostalCode {
        private final String _postalCode;
        PostalCode(String postalCode) { _postalCode = postalCode; }
        String get() { return _postalCode; }
    }
}
