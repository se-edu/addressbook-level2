package seedu.addressbook.commands;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

//show the addressbook list of people under command "showList".
public class ShowListCommand extends Command {

    public static final String COMMAND_WORD = "showList";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the list of people in address book.\n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> personList = addressBook.getAllPersons().immutableListView();
        return new CommandResult(setList(personList));

    }

    /**
     * Formats the stats message that informs the user of statistics.
     * @param personList The list of persons to read from.
     * @return A string representation of the statistics of personList.
     */
    public String setList(List<ReadOnlyPerson> personList) {
        int numPeople = personList.size();
        return String.format(
                "Current number of people in address book : %1$s!", numPeople) + personList.toString();
    }


}
