package seedu.addressbook.data.person.address;

/**
 * Represents an address' unit in an address.
 * Guarantees: immutable;
 */
public class Unit {
	
	private final String unit;
	
	public Unit(String unit) {
		this.unit = unit;
	}
	
	public String getValue() {
		return unit;
	}
	
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && this.getValue().equals(((Unit) other).getValue())); // state check
    }
}
