package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Block {

    public static final String EXAMPLE = "123";
    public static final String MESSAGE_BLOCK_CONSTRAINTS = "Block should only contain numbers";
    public static final String BLOCK_VALIDATION_REGEX = "\\d{1,3}\\D?";
    public final String value;

    public Block(String block) throws IllegalValueException {
        if (!isValidBlock(block)) {
            throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
        }
        this.value = block;
    }

    private boolean isValidBlock(String test)  {
        return (test.matches(BLOCK_VALIDATION_REGEX));
    }

    @Override
    public String toString() {
        return value;
    }
}
