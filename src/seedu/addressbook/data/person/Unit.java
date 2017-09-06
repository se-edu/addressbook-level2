package seedu.addressbook.data.person;

public class Unit {
    private String value;

    public Unit(String unit) {
        value = unit;
    }

    public String getUnit() {
        return value;
    }

    public boolean isValid() {
        return true;
    }
}
