package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book. Guarantees: immutable; is
 * valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

	public static final String EXAMPLE = "123, some street, unit, postal code";
	public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
	public static final String ADDRESS_VALIDATION_REGEX = ".+";
	public static final String ADDRESS_DELIMITER = ", ";
	private static int INDEX_BLOCK = 0;
	private static int INDEX_STREET = 1;
	private static int INDEX_UNIT = 2;
	private static int INDEX_POSTALCODE = 3;
	private final Block blkNo;
	private final Street streetName;
	private final Unit unitNo;
	private final PostalCode postalCode;

	private boolean isPrivate;

	/**
	 * Validates given address.
	 *
	 * @throws IllegalValueException
	 *             if given address string is invalid.
	 */
	public Address(String address, boolean isPrivate) throws IllegalValueException {
		String trimmedAddress = address.trim();
		if (!isValidAddress(trimmedAddress)) {
			throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
		}
		String[] SeperateAddress = trimmedAddress.split(ADDRESS_DELIMITER);
		this.blkNo = new Block(SeperateAddress[INDEX_BLOCK], isPrivate);
		this.streetName = new Street(SeperateAddress[INDEX_STREET], isPrivate);
		this.unitNo = new Unit(SeperateAddress[INDEX_UNIT], isPrivate);
		this.postalCode = new PostalCode(SeperateAddress[INDEX_POSTALCODE], isPrivate);

	}

	/**
	 * Returns true if a given string is a valid person address.
	 */
	public static boolean isValidAddress(String test) {
		return test.matches(ADDRESS_VALIDATION_REGEX);
	}

	@Override
	public String toString() {
		String Address = blkNo.toString() + ADDRESS_DELIMITER + streetName.toString() + ADDRESS_DELIMITER
				+ unitNo.toString() + ADDRESS_DELIMITER + postalCode.toString();
		return Address;
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof Address // instanceof handles nulls
						&& this.toString().equals(((Address) other).toString())); // state
																					// check
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	public boolean isPrivate() {
		return isPrivate;
	}
}
