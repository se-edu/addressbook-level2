package seedu.addressbook.data.person;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address' block in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidBlock(String)}
 */
public class Block {

    public static final String EXAMPLE = "123";
    public static final String MESSAGE_BLOCK_CONSTRAINTS =
            "Person address' block should only contain numbers";
    public static final String BLOCK_VALIDATION_REGEX = "\\d+";
    
    public final String value;


    /**
     * Validates given address block.
     *
     * @throws IllegalValueException if given block string is invalid.
     */
    Block(String block) throws IllegalValueException {
        if(!isValidBlock(block)){
            throw new IllegalValueException(MESSAGE_BLOCK_CONSTRAINTS);
        }
        
        this.value = block;
    }

    public static boolean isValidBlock(String test) {
        return test.matches(BLOCK_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
