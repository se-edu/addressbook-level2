package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.Utils;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.TextUi.*;

/**
 * Shows all details of the person identified using the last displayed index.
 * Private contact details are shown.
 */
public class ViewAllPersonDetailsCommand extends TargetLastListedPersonCommand {

    public static final String COMMAND_WORD = "viewall";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the non-private details of the person "
            + "identified by the index number in the last shown person listing."
            + LS + "Parameters: INDEX"
            + LS + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Viewing person: %1$s";

    private AddressBook addressBook;

    public ViewAllPersonDetailsCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

    @Override
    public void injectDependencies(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
        this.relevantPersons = relevantPersons;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetFromView();
            if (!addressBook.containsPerson(target)) {
                return new CommandResult(MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_DETAILS, target.getAsTextShowAll()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
