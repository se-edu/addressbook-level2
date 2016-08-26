package seedu.addressbook.data.person;


public class Block {

    public static final String BLOCK_VALIDATION_REGEX = "\\d+";
    
    public final String value;

    
    public Block(String block) {
        this.value = block;
        
    }


    /**
     * Returns true if a given string is a valid person block.
     */
    public static boolean isValidBlock(String block) {
        return block.matches(BLOCK_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
