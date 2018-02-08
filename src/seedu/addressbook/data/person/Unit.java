package seedu.addressbook.data.person;

/**
 * Represents a Person's unit address in the address book.
 * Guarantees: immutable;
 */
public class Unit {
    private final String unit;

    public Unit(String unit){
        String trimmedUnit = unit.trim();
        this.unit = trimmedUnit;
    }

    @Override
    public String toString(){
        return unit;
    }

    public String getUnit(){
        return unit;
    }
}
