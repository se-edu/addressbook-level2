package seedu.addressbook.data.person;

public class Block {
	public static final String BLOCK_VALIDATION_REGEX = "\\d+";
	public final String value;
	
	public Block(String block) {
		value = block;
	}
	
    public static boolean isValid(String test) {
        return test.matches(BLOCK_VALIDATION_REGEX);
    }
}
