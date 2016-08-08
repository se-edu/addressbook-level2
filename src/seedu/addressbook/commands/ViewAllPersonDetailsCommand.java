package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

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

    private final AddressBook addressBook;

    /**
     * @param args full command args string from the user
     * @param addressBook address book containing person to view
     * @param view ui holding the previous viewed person listing
     */
    public ViewAllPersonDetailsCommand(String args, AddressBook addressBook, TextUi view) {
        super(args, view);
        this.addressBook = addressBook;
    }

    @Override
    public String execute() {
        if (!isValidArgs(args)) {
            return String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);
        }
        try {
            final ReadOnlyPerson target = extractTargetFromArgs();
            if (!addressBook.containsPerson(target)) {
                return MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;
            }
            return String.format(MESSAGE_VIEW_PERSON_DETAILS, target.getAsTextShowAll());
        } catch (NumberFormatException | IndexOutOfBoundsException ie) {
            return MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        }
    }
}
