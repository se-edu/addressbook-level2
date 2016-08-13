package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;
import seedu.addressbook.model.person.UniquePersonList.PersonNotFoundException;

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

    /**
     * @param args full command args string from the user
     * @param addressBook address book person will be deleted from
     * @param view ui holding the previous viewed person listing
     */
    public DeletePersonCommand(String args, AddressBook addressBook, TextUi view) {
        super(args, view);
        this.addressBook = addressBook;
    }

    @Override
    public void injectDependencies(TextUi ui, AddressBook addressBook) {
        this.addressBook = addressBook;
        this.view = ui;
    }

    @Override
    public String execute() {
        if (!isValidArgs(args)) {
            return String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);
        }
        try {
            final ReadOnlyPerson target = extractTargetFromArgs();
            addressBook.removePerson(target);
            return String.format(MESSAGE_DELETE_PERSON_SUCCESS, target);

        } catch (NumberFormatException | IndexOutOfBoundsException ie) {
            return MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        } catch (PersonNotFoundException pnfe) {
            return MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;
        }
    }

}
