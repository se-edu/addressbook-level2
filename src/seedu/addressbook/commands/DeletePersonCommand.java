package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.Utils;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;
import seedu.addressbook.model.person.UniquePersonList.PersonNotFoundException;

import java.util.List;

import static seedu.addressbook.TextUi.*;

/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class DeletePersonCommand extends TargetLastListedPersonCommand {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the last person listing."
            + LS + "Parameters: INDEX"
            + LS + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";

    private AddressBook addressBook;

    public DeletePersonCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    @Override
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
        this.relevantPersons = relevantPersons;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetFromView();
            addressBook.removePerson(target);
            return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

}
