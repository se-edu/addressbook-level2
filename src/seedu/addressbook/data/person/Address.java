package seedu.addressbook.data.person;

import java.util.Objects;

/**
 * Represents a Person's address in the address book.
 */
public class Address {

    public Address address;
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

    private boolean isPrivate;

    public Address(Block block, Street street, Unit unit, PostalCode postalCode, boolean isPrivate) {
        this.block = block;
        this.street = street;
        this.unit = unit;
        this.postalCode = postalCode;
        this.isPrivate = isPrivate;
    }

    public Address(String address, boolean isPrivate) {
        this.address = address;
        this.isPrivate = isPrivate;
    }

    String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        final String detailIsPrivate = "(private) ";
        builder.append(getBlock())
                .append(" Phone: ");
        if (getStreet().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getUnit())
                .append(" Email: ");
        if (getPostalCode().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        return builder.toString();
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
