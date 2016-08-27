package seedu.addressbook.data.person.address;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "a/123, some street, unit 102, 123456";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses must be in the format \"a\\BLOCK, STREET, UNIT, POSTAL_CODE\"";
    public static final String ADDRESS_VALIDATION_REGEX = "a\\/(.*), (.*), (.*), (.*)";

	private final Block block;
    private final Street street;
    private final Unit unit;
    private final PostalCode postalCode;
    private boolean isPrivate;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        Pattern p = Pattern.compile(ADDRESS_VALIDATION_REGEX);
        Matcher m = p.matcher(address);
        if (m.find()) {
        	this.block = new Block(m.group(1));
        	this.street = new Street(m.group(2));
        	this.unit = new Unit(m.group(3));
        	this.postalCode  = new PostalCode(m.group(4));
        } else {
        	throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
    }

    /**
     * Creates address from specified information.
     *
     */
    public Address(String block, String street, String unit, String postalCode, boolean isPrivate) {
        this.isPrivate = isPrivate;
    	this.block = new Block(block);
    	this.street = new Street(street);
    	this.unit = new Unit(unit);
    	this.postalCode  = new PostalCode(postalCode);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
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
        return "a/" +
        		block.getValue() + ", " +
        		street.getValue() + ", " +
        		unit.getValue() + ", " +
        		postalCode.getValue();
    }

    @Override
    public boolean equals(Object other) {
    	if (other == this) {
    		return true;
    	} else if (other instanceof Address) {
    		Address that = (Address) other;
    		return this.block.equals(that.block) &&
    			   this.street.equals(that.street) &&
    			   this.unit.equals(that.unit) &&
    			   this.postalCode.equals(that.postalCode);
    	}
        return false;
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}