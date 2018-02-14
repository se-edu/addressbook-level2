package seedu.addressbook.data.person;

/**
 * Representation of a unit in address. Immutable.
 */
public class Unit {
    public final String unit;
    public Unit(String unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                o instanceof Unit &&
                ((Unit) o).unit == unit;
    }

    @Override
    public int hashCode() {
        return unit.hashCode();
    }

}
