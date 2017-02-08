package seedu.addressbook.data.person;

/**
 * Represents a Person's postal code address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */

public class PostalCode {

	public final String postalCode;
	
	public PostalCode(String newPostalCode) {
		this.postalCode = newPostalCode;
	}

}
