package seedu.addressbook.data.person;


public class Unit {

    public static final String UNIT_VALIDATION_REGEX = "\\d+";

    public final String value;

    public Unit(String unit) {
        this.value = unit;
    }

    public static boolean isValidUnit(String unit) {
        return unit.matches(UNIT_VALIDATION_REGEX);
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
