package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Block {

    public final String value;
    public final String BLOCK_NAME_CONSTRAINTS = "Address block should only contain alphabets and numbers";
    public static final String BLOCK_VALIDATION_REGEX = "[\\p{Alnum}]+";

    public Block(String value) {
        String trimmedValue = value;
        this.value = trimmedValue;
    }

    public static boolean isValidBlock(String test) {
        return test.matches(BLOCK_VALIDATION_REGEX);
    }
    public String getBlock()  {
        return value;
    }
}
