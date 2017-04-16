package seedu.addressbook.data.person;

/**
 * Represents a Person's street address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */

public class Street {

	public final String street;
	
	public Street(String newStreet) {
		this.street = newStreet;
	}
}