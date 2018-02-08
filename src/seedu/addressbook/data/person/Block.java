package seedu.addressbook.data.person;

/**
 * Represents a Person's block address in the address book.
 * Guarantees: immutable;
 */

public class Block {
    //public final String value;
    private boolean isPrivate;
    public final String value;

    public Block(String blockAddress, boolean isPrivate) {
        String trimmedBlockAddress = blockAddress.trim();
        this.isPrivate = isPrivate;
        this.value = trimmedBlockAddress;
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Block // instanceof handles nulls
                && this.value.equals(((Block) other).value)); // state check
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}