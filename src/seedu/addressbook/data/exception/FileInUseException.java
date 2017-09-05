package seedu.addressbook.data.exception;

/***
 * Signals that a critical system process is using a file a process is trying to delete.
 */
public class FileInUseException extends Exception {
    public FileInUseException(String message) {
        super(message);
    }
}
