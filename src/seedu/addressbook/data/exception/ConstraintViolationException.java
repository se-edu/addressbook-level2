package seedu.addressbook.data.exception;

/**
 * Signals that the operation should not take place as it will violate some constraint.
 */
public class ConstraintViolationException extends Exception {
    /**
     * @param message should contain relevant information on the failed constraint(s)
     */
    public ConstraintViolationException(String message) {
        super(message);
    }
}
