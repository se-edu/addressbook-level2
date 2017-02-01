package seedu.addressbook.data.person;

public class PostalCode {
	public static final String POSTALCODE_VALIDATION_REGEX = "\\d+";
	public final String value;
	
	public PostalCode(String postalCode) {
		value = postalCode;
	}
    public static boolean isValid(String test) {
        return test.matches(POSTALCODE_VALIDATION_REGEX);
    }
}
