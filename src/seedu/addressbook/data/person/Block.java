package seedu.addressbook.data.person;

/**
 * Represents a Person's block address in the address book.
 * Guarantees: immutable;
 */
public class Block {
    private final String block;

    public Block(String block){
        String trimmedBlock = block.trim();
        this.block = trimmedBlock;
    }

    @Override
    public String toString(){
        return block;
    }

    public String getBlock(){
        return block;
    }
}
