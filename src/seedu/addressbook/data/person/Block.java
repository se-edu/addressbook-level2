package seedu.addressbook.data.person;
/**
 * Represents a Block in a Person's address in the address book.
 */

public class Block {
    private String blockNumber;

    public Block() {
        blockNumber = "";
    }

    public void setBlockNumber(String b) {
        blockNumber = b;
    }

    public String getBlockNumber() {
        return blockNumber;
    }
}
