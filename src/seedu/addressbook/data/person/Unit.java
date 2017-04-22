package seedu.addressbook.data.person;

public class Unit {
	public static final String UNIT_VALIDATION_REGEX = "#\\d+-\\d+";
	public final String value;
	
	public Unit(String unit) {
		value = unit;
	}
    public static boolean isValid(String test) {
        return test.matches(UNIT_VALIDATION_REGEX);
    }
}
