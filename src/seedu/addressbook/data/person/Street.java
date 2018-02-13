package seedu.addressbook.data.person;

public class Street {

    public final String value;
    public final String STREET_NAME_CONSTRAINTS = "Street should only contain alphabets and numbers";
    public static final String STREET_VALIDATION_REGEX = "[\\p{Alnum}]+";

    public Street(String value) {
        String trimmedValue = value;
        this.value = trimmedValue;
    }

    public static boolean isValidBlock(String test) {
        return test.matches(STREET_VALIDATION_REGEX);
    }
    public String getBlock()  {
        return value;
    }
}
