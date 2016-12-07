package seedu.addressbook.commands;

import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Represents an executable command.
 */
public interface Command {

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute();

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons);
}
