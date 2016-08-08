package seedu.addressbook.commands;

/**
 *
 */
@FunctionalInterface
public interface Command {

    /**
     * Executes the command and returns the result message.
     *
     * @return feedback message of the operation result for display
     */
    String execute();

}
