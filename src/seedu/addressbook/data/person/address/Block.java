package seedu.addressbook.data.person.address;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 *
 * Represents the block number of an address.
 *
 */
public class Block {
    public static final String EXAMPLE = "123";
    public static final String MESSAGE_NAME_CONSTRAINTS = "Block numbers should only consist of numbers.";
    public static final String BLOCK_VALIDATION_REGEX = "\\d+";
    private String value;

    public Block(String blockNumber) throws IllegalValueException {
        String trimmedBlockNumber = blockNumber.trim();
        if (!isValidBlockNumber(trimmedBlockNumber)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.value = blockNumber;
    }

    /**
     * Returns true if the given string is a valid person name.
     */
    public static boolean isValidBlockNumber(String test) {
        return test.matches(BLOCK_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.value.equals(((Block) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return this.value.hashCode();
    }
}
