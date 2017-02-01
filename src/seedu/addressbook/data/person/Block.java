package seedu.addressbook.data.person;

/**
 * Represents a block in a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Block {

	private final String block;
	
	public Block(String block) {
		this.block = block;
	}
	
	public String getBlock() {
		return block;
	}
}
