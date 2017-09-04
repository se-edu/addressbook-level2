package seedu.addressbook.data.address;

/**
 * Represents a block in an address.
 */
public class Block {
    private final String block;

    public Block(String block) {
        this.block = block;
    }

    public String toString() {
        return this.block;
    }
}