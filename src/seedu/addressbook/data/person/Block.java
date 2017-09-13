package seedu.addressbook.data.person;

public class Block {
    private int blockNum;
    public Block(int bNum) { this.blockNum = bNum;}

    public int getBlock() {
        return blockNum;
    }

    public void setBlock(int blockNumber) {
        this.blockNum = blockNum;
    }
}