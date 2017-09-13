package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street, #01-01, 319771";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses are in format: " +
            "Block, Street, Unit, Postal_Code";
    // to match \\any digit, [any alphanumeric with blanks], #\\any digit-digit, \\any 6digits
    public static final String ADDRESS_VALIDATION_REGEX = "\\d+, [\\w\\d\\s]+, #\\d+-\\d+, \\d{6}$";

    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;
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
        block = new Block(splitAddress[Block.ADDRESS_INDEX]);
        street = new Street(splitAddress[Street.ADDRESS_INDEX]);
        unit = new Unit(splitAddress[Unit.ADDRESS_INDEX]);
        postalCode =new PostalCode(splitAddress[PostalCode.ADDRESS_INDEX]);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", block, street, unit, postalCode);
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
        public static final int ADDRESS_INDEX = 0;

        private final String block;
        private Block(String block) {
            this.block = block;
        }

        @Override
        public String toString() {
            return block;
        }
    }

    private class Street {
        public static final int ADDRESS_INDEX = 1;

        private final String street;
        private Street(String street) {
            this.street = street;
        }

        @Override
        public String toString() {
            return street;
        }
    }

    private class Unit {
        public static final int ADDRESS_INDEX = 2;

        private final String unit;
        private Unit(String unit) {
            this.unit = unit;
        }

        @Override
        public String toString() {
            return unit;
        }
    }

    private class PostalCode {
        public static final int ADDRESS_INDEX = 3;

        private final String postalCode;
        private PostalCode(String postalCode) {
            this.postalCode = postalCode;
        }

        @Override
        public String toString() {
            return postalCode;
        }
    }
}

