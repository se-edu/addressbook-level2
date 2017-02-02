package seedu.addressbook.data.person.address;

public class Unit {
	
	private String unit;
	private final String NO_UNIT = "";
	
	public Unit(){
		this.unit = NO_UNIT;
	}
	
	public Unit(String unit) {
		this.unit = unit;
	}
	
	public int getUnit(){
		return this.unit;
	}
}
