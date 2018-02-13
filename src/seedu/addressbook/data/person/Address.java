package seedu.addressbook.data.person;

import java.util.Objects;

/**
 * Represents a Person's address in the address book.
 */
public class Address {

    public Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;

    public Block getBlock() {
        return block;
    }

    public Street getStreet() {
        return street;
    }

    public Unit getUnit() {
        return unit;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public final String value;
    private boolean isPrivate;

    public Address(Block block, Street street, Unit unit, PostalCode postalCode, String value, boolean isPrivate) {
        this.block = block;
        this.street = street;
        this.unit = unit;
        this.postalCode = postalCode;
        this.value = value;
        this.isPrivate = isPrivate;
    }


    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.equals((Address) other));
    }

    @Override
    public int hashCode() {
//        return value.hashCode();
        return Objects.hash(block, street, unit, postalCode, isPrivate);
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
