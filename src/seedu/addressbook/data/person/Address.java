package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Person's address in the address book.
 */
public class Address {
//    private static final String ADDRESS_SPLIT_REGEX = ",";
//    private static final String SEPARATOR = ", ";
//    private static final int BLOCK_INDEX = 0;
//    private static final int STREET_INDEX = 1;
//    private static final int UNIT_INDEX = 2;
//    private static final int POSTALCODE_INDEX = 3;

    public static final String EXAMPLE = "123, some street, #01-01, 123456";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format"
            + "'BLOCK, STREET, UNIT, POSTAL CODE'";
//    public static final String ADDRESS_VALIDATION_REGEX = ".+?(?=,),.+?(?=,),.+?(?=,),.+";
    public static final Pattern ADDRESS_ARGS_FORMAT = //commas are reserved for delimeter prefixes
        Pattern.compile("(?<block>[^,]+), (?<street>[^,]+)" + ", (?<unit>[^,]+), (?<postalCode>[^,]+)");

    private static Matcher matcher;
    private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;
//    public final String value;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
//        String[] splitedAddress = trimmedAddress.split(ADDRESS_VALIDATION_REGEX);
//        this.block = new Block(splitedAddress[BLOCK_INDEX], isPrivate);
//        this.street = new Street(splitedAddress[STREET_INDEX], isPrivate);
//        this.unit = new Unit(splitedAddress[UNIT_INDEX], isPrivate);
//        this.postalCode = new PostalCode(splitedAddress[POSTALCODE_INDEX], isPrivate);
//        this.value = block.toString() + SEPARATOR + street.toString() + SEPARATOR + unit.toString()
//                        + SEPARATOR + postalCode;
        this.block = new Block(matcher.group("block"));
        this.street = new Street(matcher.group("street"));
        this.unit = new Unit(matcher.group("unit"));
        this.postalCode = new PostalCode(matcher.group("postalCode"));
        this.isPrivate = isPrivate;
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
//        return test.matches(ADDRESS_VALIDATION_REGEX);
        matcher = ADDRESS_ARGS_FORMAT.matcher(test);
        return matcher.matches();
    }

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

    @Override
    public String toString() {
//        return value;
        final StringBuilder builder = new StringBuilder();
        builder.append(getBlock() + ", ")
                .append(getStreet() + ", ")
                .append(getUnit() + ", ")
                .append(getPostalCode());
        return builder.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
//                && this.value.equals(((Address) other).value)); // state check
                && this.hasSameData((Address) other)); // state check

    }

    private boolean hasSameData(Address other) {
        return other == this // short circuit if same object
                                || (other != null // this is first to avoid NPE below
                                && this.getBlock().equals(other.getBlock()) // state checks here onwards
                                && this.getStreet().equals(other.getStreet())
                                && this.getUnit().equals(other.getUnit())
                                && this.getPostalCode().equals(other.getPostalCode()));
    }

    @Override
    public int hashCode() {
//        return value.hashCode();
        return Objects.hash(block, street, unit, postalCode);
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
