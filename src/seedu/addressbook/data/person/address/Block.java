package seedu.addressbook.data.person.address;
import seedu.addressbook.data.exception.IllegalValueException;

public class Block {

    public static final String EXAMPLE = "257";
    public static final String MESSAGE_BLOCK_CONSTRAINTS = "Address Block numbers should only contain numbers and only can have 3 digts";
    public static final String BLOCK_VALIDATION_REGEX = "\\d{1,3}";

    public final String value;

    /**
     * Validates given block number.
     *
     * @throws IllegalValueException if given block string is invalid.
     */
    public Block(String block) throws IllegalValueException {
        String trimmedBlock = block.trim();
        if (!isValidBlock(trimmedBlock)) {
            throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
        }
        this.value = trimmedBlock;
    }

    /**
     * Returns true if the given string is a valid address block number.
     */
    public static boolean isValidBlock(String test) {
        return test.matches(BLOCK_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.value.equals(((Block) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
