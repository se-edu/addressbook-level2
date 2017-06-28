package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBlock(String)}
 */
public class Block {

    public static final String EXAMPLE = "123, some street, unit, postal code";
    public static final String MESSAGE_BLOCK_CONSTRAINTS = "Person block can be in any format";
    public static final String BLOCK_VALIDATION_REGEX = ".+";

    private final String value;
    
    private boolean isPrivate;
    
    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Block(String block, boolean isPrivate) throws IllegalValueException {
        String trimmedBlock = block.trim();
        this.isPrivate = isPrivate;
        if (!isValidBlock(trimmedBlock)) {
            throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
     
        }
        this.value = trimmedBlock;
  
    }

    /**
     * Returns true if a given string is a valid person address.
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
                || (other instanceof Block
                && this.toString().equals(((Block) other).toString())); // state check
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
