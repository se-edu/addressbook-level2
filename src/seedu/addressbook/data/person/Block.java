package seedu.addressbook.data.person;

/**
 * Represents a Person's block address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */

public class Block {

	public final String block;

	public Block(String newBlock) {
		this.block = newBlock;
	}
	
}