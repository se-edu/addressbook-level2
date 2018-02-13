package seedu.addressbook.data.person;

public class PostalCode {

    public final String value;
    public final String BLOCK_NAME_CONSTRAINTS = "Postal code should only contain numbers";
    public static final String POSTALCODE_VALIDATION_REGEX = "[\\d]+";

    public PostalCode(String value) {
        String trimmedValue = value;
        this.value = trimmedValue;
    }

    public static boolean isValidBlock(String test) {
        return test.matches(POSTALCODE_VALIDATION_REGEX);
    }
    public String getBlock()  {
        return value;
    }

}
