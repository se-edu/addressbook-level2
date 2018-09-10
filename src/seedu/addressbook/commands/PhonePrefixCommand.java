package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Finds and lists all persons in address book whose phone number starts with the given prefix.
 */
public class PhonePrefixCommand extends Command {

    public static final String COMMAND_WORD = "prefix";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose phone numbers start with "
            + "the specified prefix and displays them as a list with index numbers.\n"
            + "Parameters: PREFIX \n"
            + "Example: " + COMMAND_WORD + " 65";

    private final String prefix;

    public PhonePrefixCommand(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public String getPrefix() {
        return prefix;
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithPhonePrefix(prefix);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose phone numbers start with the given prefix.
     *
     * @param prefix for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithPhonePrefix(String prefix) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final String phoneNumber = person.getPhone().toString();
            if (phoneNumber.startsWith(prefix)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
