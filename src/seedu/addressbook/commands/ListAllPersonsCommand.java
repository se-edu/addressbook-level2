package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.Utils;
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

    private AddressBook addressBook;
    private TextUi ui;

    public ListAllPersonsCommand() {}

    @Override
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }
}
