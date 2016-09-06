package seedu.addressbook.data.person.address;

/**
 * Represents an address' street in an address.
 * Guarantees: immutable;
 */
public class Street {

	private final String street;
	
	public Street(String street) {
		this.street = street;
	}
	
	public String getValue() {
		return street;
	}
	
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Street // instanceof handles nulls
                && this.getValue().equals(((Street) other).getValue())); // state check
    }
}
