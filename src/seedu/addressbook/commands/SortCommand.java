package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import seedu.addressbook.data.person.UniquePersonList;

/**
 * Lists all persons in the address book to the user.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_DUPLICATE_PERSON = "It's here because I shared a constructor with add.";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as a list with index numbers " 
            + "sorted by alphabetical order.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        try {
            List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().sort().immutableListView();
            return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
        
    }
}
