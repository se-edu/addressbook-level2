package seedu.addressbook.data.address;

public class block {

    public String blockNumber;

    public block(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    public final void setBlock(String blockNumber) {
        this.blockNumber = blockNumber;
    }

    @Override
    public String toString() { return "Block: " + blockNumber; }

}
