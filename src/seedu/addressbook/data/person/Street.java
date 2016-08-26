package seedu.addressbook.data.person;


public class Street {
    public static final String STREET_VALIDATION_REGEX = ".+";

    public final String value;

    public Street(String street) {
        this.value = street;
    }

    public static boolean isValidStreet(String street) {
        return street.matches(STREET_VALIDATION_REGEX);
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
