package seedu.addressbook.data.person.address;

/**
 * Represents an address' block in an address.
 * Guarantees: immutable;
 */
public class Block {
	
	private final String block;
	
	public Block(String block) {
		this.block = block;
	}
	
	public String getValue() {
		return block;
	}
	
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.getValue().equals(((Block) other).getValue())); // state check
    }
}
