package seedu.addressbook.data.person;

public class Street {
	public static final String STREET_VALIDATION_REGEX = ".+";
	public final String value;
	
	public Street(String street) {
		value = street;
	}
    public static boolean isValid(String test) {
        return test.matches(STREET_VALIDATION_REGEX);
    }
}
