package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's phone number in the address book. Guarantees:
 * immutable; is valid as declared in {@link #isValidPhone(String)}
 */

public class Phone extends Contact implements Printable{

	public static final String EXAMPLE = "123456789";
	public static final String MESSAGE_PHONE_CONSTRAINTS = "Person phone numbers should only contain numbers";
	public static final String PHONE_VALIDATION_REGEX = "\\d+";

	/**
	 * Validates given phone number.
	 *
	 * @throws IllegalValueException
	 *             if given phone string is invalid.
	 */
	public Phone(String phone, boolean isPrivate) throws IllegalValueException {
		super(phone, isPrivate);

		if (!isValidPhone(phone.trim())) {
			throw new IllegalValueException(MESSAGE_PHONE_CONSTRAINTS);
		}
	}

	/**
	 * Checks if a given string is a valid person phone number.
	 */
	public static boolean isValidPhone(String test) {
		return test.matches(PHONE_VALIDATION_REGEX);
	}

	@Override
	public String toString() {
		return value;
	}

	@Override
	public boolean equals(Object other) {
		return other == this // short circuit if same object
				|| (other instanceof Phone // instanceof handles nulls
						&& this.value.equals(((Phone) other).value)); // state
																		// check
	}

	@Override
	public int hashCode() {
		return value.hashCode();
	}

	public boolean isPrivate() {
		return isPrivate;
	}
	
	@Override
	public String getPrintableString() {

		return "Phone: " + this.toString();

	}


}
