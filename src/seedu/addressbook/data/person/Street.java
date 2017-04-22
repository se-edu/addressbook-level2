package seedu.addressbook.data.person;

/**
 * Represents a street in a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Street {
	
	private final String street;
	
	public Street(String street) {
		this.street = street;
	}
	
	public String getStreet() {
		return street;
	}
}
