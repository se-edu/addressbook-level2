package seedu.addressbook.data.person;

/**
 * Represents a Person's address's block in the address book.
 */
public class Block {

    public final String value;

    /**
     * Validates given address's block.
     */
    public Block(String block) {
        this.value = block;
    }

}