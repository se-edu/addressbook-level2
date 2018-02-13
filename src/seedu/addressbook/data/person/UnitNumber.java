package seedu.addressbook.data.person;

public class UnitNumber {

    public final String value;
    public final String UNITNUMBER_NAME_CONSTRAINTS = "Unit Number should be in the format of #No.-No.";
    public static final String BLOCK_VALIDATION_REGEX = "[^\\p{Alpha}]+";

    public UnitNumber(String value) {
        String trimmedValue = value;
        this.value = trimmedValue;
    }

    public static boolean isValidBlock(String test) {
        return test.matches(BLOCK_VALIDATION_REGEX);
    }

    public String getBlock()  {
        return value;
    }
}
