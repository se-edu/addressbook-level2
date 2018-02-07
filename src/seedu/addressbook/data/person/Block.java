package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents the block number in a Person's address.
 * Guarantees: immutable; is valid as declared in {@link #isValidBlock(String)}
 */
public class Block {

    public static final String EXAMPLE = "311";
    private static final String MESSAGE_BLOCK_CONSTRAINTS = "Block numbers should only contain numbers";
    private static final String BLOCK_VALIDATION_REGEX = "\\d+";

    public final String value;

    public Block(String blkNo) {
        value = blkNo;
    }

    /**
     * Returns true if the given string is a valid block number.
     */
    public static boolean isValidBlock(String test) throws IllegalValueException{
        if(!test.matches(BLOCK_VALIDATION_REGEX)){
            throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
        }
        return true;
    }

    @Override
    public String toString(){
        return value;
    }
}