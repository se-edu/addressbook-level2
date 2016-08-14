package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.List;

/**
 * Represents a command with hidden internal logic and the ability to be executed.
 */
public interface Command {

    /**
     * Executes the command and returns the result message.
     *
     * @return feedback message of the operation result for display
     */
    CommandResult execute();

    /**
     * Provides any needed dependencies to the command.
     * Commands making use of any of these should override this method to gain
     * access to the dependencies.
     */
    default void injectDependencies(TextUi ui, AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {}
}
