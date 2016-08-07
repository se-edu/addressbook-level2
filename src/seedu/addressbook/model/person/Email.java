package seedu.addressbook.model.person;

import seedu.addressbook.Utils;
import seedu.addressbook.model.InvalidDataException;

/**
 * Represents a Person's phone number in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email extends ContactDetail {

    public static final String MESSAGE_EMAIL_CONSTRAINTS =
            "Person emails should be 2 alphanumeric/period strings separated by '@'";
    public static final String EMAIL_VALIDATION_REGEX = "[\\w\\.]+@[\\w\\.]+";

    public final String emailAddress;

    /**
     * Validates given email.
     *
     * @throws InvalidDataException if given email address string is invalid.
     */
    public Email(String email, boolean isPrivate) throws InvalidDataException {
        super(isPrivate);
        Utils.assertNotNull(email);
        email = email.trim();
        if (!isValidEmail(email)) {
            throw new InvalidDataException(MESSAGE_EMAIL_CONSTRAINTS);
        }
        this.emailAddress = email;
    }

    /**
     * Checks if a given string is a valid person email.
     */
    public static boolean isValidEmail(String test) {
        return test.matches(EMAIL_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return emailAddress;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && this.emailAddress.equals(((Email) other).emailAddress)); // state check
    }

    @Override
    public int hashCode() {
        return emailAddress.hashCode();
    }


}