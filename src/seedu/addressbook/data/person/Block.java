package seedu.addressbook.data.person;

/**
 * Representation of a block in address. Immutable.
 */
public class Block {
    public final String block;
    public Block(String block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return block;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                o instanceof Block &&
                ((Block) o).block == block;
    }

    @Override
    public int hashCode() {
        return block.hashCode();
    }

}
