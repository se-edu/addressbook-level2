package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.List;

import static seedu.addressbook.TextUi.*;

/**
 * Lists all persons in the address book to the user.
 */
public class ListAllPersonsCommand implements Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers."
            + LS + "Example: " + COMMAND_WORD;

    private final AddressBook addressBook;
    private final TextUi ui;

    /**
     *
     * @param addressBook containing persons to list
     * @param ui for displaying the list of found persons
     */
    public ListAllPersonsCommand(AddressBook addressBook, TextUi ui) {
        this.addressBook = addressBook;
        this.ui = ui;
    }

    @Override
    public String execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersonsImmutableView();
        ui.showPersonListView(allPersons);
        return getMessageForPersonListShownSummary(allPersons);
    }
}
