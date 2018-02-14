package seedu.addressbook.data.person;

/**
 * Representation of a postalCode in address. Immutable.
 */
public class PostalCode {
    public final String postalCode;
    public PostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return postalCode;
    }

    @Override
    public boolean equals(Object o) {
        return this == o ||
                o instanceof PostalCode &&
                ((PostalCode) o).postalCode == postalCode;
    }

    @Override
    public int hashCode() {
        return postalCode.hashCode();
    }

}
