package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's email in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidEmail(String)}
 */
public class Email {

    public static final String EXAMPLE = "valid@e.mail";
    public static final String MESSAGE_EMAIL_CONSTRAINTS =
            "Person emails should be 2 alphanumeric/period strings separated by '@'";
    public static final String EMAIL_VALIDATION_REGEX = "[\\w\\.]+@[\\w\\.]+";
    public static final String EMAIL_PLACEHOLDER = " ";

    public final String value;
    private boolean isPrivate;

    /**
     * Validates given email.
     *
     * @throws IllegalValueException if given email address string is invalid.
     */
    public Email(String email, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (email == null){
            this.value = EMAIL_PLACEHOLDER;
            this.isPrivate = isPrivate;
        } else if (email.equals(EMAIL_PLACEHOLDER)){
            this.value = EMAIL_PLACEHOLDER;
            this.isPrivate = isPrivate;
        } else if (!isValidEmail(email)) {
            throw new IllegalValueException(MESSAGE_EMAIL_CONSTRAINTS);
        } else {
            this.value = email.trim();
            this.isPrivate = isPrivate;
        }
    }

    /**
     * Returns true if the given string is a valid person email.
     */
    public static boolean isValidEmail(String test) {
        String trimmed = test.trim();
        return trimmed.matches(EMAIL_VALIDATION_REGEX) || test.equals(EMAIL_PLACEHOLDER);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Email // instanceof handles nulls
                && this.value.equals(((Email) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }


    public boolean isPrivate() {
        return isPrivate;
    }
}
