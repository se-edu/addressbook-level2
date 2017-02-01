package seedu.addressbook.data.person;

/**
 * Represents a unit in a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Unit {

	private final String unit;
	
	public Unit(String unit) {
		this.unit = unit;
	}
	
	public String getUnit() {
		return unit;
	}
}
