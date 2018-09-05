package seedu.addressbook.data.person;

public class Block {
    private static String block;

    public Block(String block) {
        this.block = block;
    }

    public static String getBlock() {
        return block;
    }
}
