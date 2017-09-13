package seedu.addressbook.data.person;

public class Unit {

    private String unit;

    public Unit(String unit) {
        this.unit = unit.trim();
    }

    public String getUnit() {
        return unit;
    }
}
