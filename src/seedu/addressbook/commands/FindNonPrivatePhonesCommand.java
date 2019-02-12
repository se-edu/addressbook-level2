package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindNonPrivatePhonesCommand extends Command {

    public static final String COMMAND_WORD = "findnpphones";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose phone numbers are"
            + "not private and displays them as a list with index numbers.\n"
            + "Example: " + COMMAND_WORD;

    public FindCommand(){}

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNonPrivatePhoneNumbers();
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book with the boolean field isPhonePrivate == false.
     *
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword() {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            if (!person.getPhone().isPrivate()) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}