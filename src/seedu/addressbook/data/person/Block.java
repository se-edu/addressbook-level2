package seedu.addressbook.data.person;

/**
 * Represents a block number in a Person's Address
 */
public class Block {

    private final String value;

    public Block(String block) {
        this.value = block.trim();
    }

    public String getValue() { return value; }

    @Override
    public String toString() { return value; }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceOf handles null
                && this.value.equals(((Block) other).getValue())); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
