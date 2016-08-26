package seedu.addressbook.data.person;



public class PostalCode {

    public static final String POSTAL_CODE_VALIDATION_REGEX = "\\d+";

    public final String value;

    public PostalCode(String postalCode) {
        this.value = postalCode;
    }

    public static boolean isValidPostalCode(String postalCode) {
        return postalCode.matches(POSTAL_CODE_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }
    
    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
