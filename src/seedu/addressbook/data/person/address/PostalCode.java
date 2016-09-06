package seedu.addressbook.data.person.address;

/**
 * Represents an address' postal code in an address.
 * Guarantees: immutable;
 */
public class PostalCode {
	
	private final String postalCode;
	
	public PostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getValue() {
		return postalCode;
	}
	
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PostalCode // instanceof handles nulls
                && this.getValue().equals(((PostalCode) other).getValue())); // state check
    }
}
