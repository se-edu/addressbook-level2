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
    public static final String ADDRESS_PLACEHOLDER = " ";

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        if (address == null){
            this.value = ADDRESS_PLACEHOLDER;
            this.isPrivate = isPrivate;
        } else if (address.equals(ADDRESS_PLACEHOLDER)){
            this.value = ADDRESS_PLACEHOLDER;
            this.isPrivate = isPrivate;
        }  else if (!isValidAddress(address)){
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        } else {
            String trimmedAddress = address.trim();
            this.value = trimmedAddress;
            this.isPrivate = isPrivate;
        }
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        String trimmed = test.trim();
        return trimmed.matches(ADDRESS_VALIDATION_REGEX) || test.equals(ADDRESS_PLACEHOLDER);
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
