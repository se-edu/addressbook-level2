package seedu.addressbook.data.person;

public class Block {

     private String block;

     public Block(String block) {
         this.block = block.trim();
     }

    public String getBlock() {
        return block;
    }
}