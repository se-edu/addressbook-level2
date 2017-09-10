package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class PostalCode {

    public static final String EXAMPLE = "123456";
    public static final String MESSAGE_POSTALCODE_CONSTRAINTS = "Postal Code to be in XXXXXX or SXXXXXX or sXXXXXX or Singapore XXXXXX or singapore XXXXXX format";
    public static final String POSTAL_VALIDATION_REGEX = "^(S|s)?(ingapore)?\\s?\\d{6}";
    public final String value;

    public PostalCode(String code) throws IllegalValueException {
        if (!isValidCode(code)) {
            throw new IllegalValueException(MESSAGE_POSTALCODE_CONSTRAINTS);
        }
        this.value = code;
    }

    private boolean isValidCode(String test) {
        return (test.matches(POSTAL_VALIDATION_REGEX));
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PostalCode // instanceof handles nulls
                && this.value.equals(((PostalCode) other).value)); // state check
    }
}
