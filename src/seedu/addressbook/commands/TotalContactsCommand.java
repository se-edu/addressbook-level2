package seedu.addressbook.commands;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

public class TotalContactsCommand extends Command {

    public static final String COMMAND_WORD = "total";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays total number of contacts in the address book.\n"
            + "Example: " + COMMAND_WORD;

    public int getTotalContacts() throws NullPointerException{
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        int totalContacts = allPersons.size();
        return totalContacts;
    }

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        String totalContacts = Integer.toString(allPersons.size());
        return new CommandResult(totalContacts);
    }
}
