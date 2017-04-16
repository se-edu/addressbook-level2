package seedu.addressbook.data.person;

/**
 * Represents a Person's address unit in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */

public class Unit {

	public final String unit;
	
	public Unit(String newUnit) {
		this.unit = newUnit;
	}
}
