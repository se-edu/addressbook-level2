package seedu.addressbook.model;

/**
 * Signals that some given data those not fulfill it's constraints.
 * Eg. trying to create a tag with a name string containing whitespace.
 *
 * {@link #getMessage()} will contain the
 */
public class InvalidDataException extends Exception {
    public InvalidDataException(String message) {
        super(message);
    }
}
