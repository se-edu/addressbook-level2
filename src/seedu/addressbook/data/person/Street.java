package seedu.addressbook.data.person;

/**
 * Representation of a street in address. Immutable.
 */
public class Street {
    public final String street;
    public Street(String street) {
        this.street = street;
    }

    @Override
    public String toString() {
        return street;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                o instanceof Street &&
                ((Street) o).street == street;
    }

    @Override
    public int hashCode() {
        return street.hashCode();
    }

}
