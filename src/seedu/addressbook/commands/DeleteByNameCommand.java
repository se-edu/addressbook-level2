package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

public class DeleteByNameCommand extends Command {

    private String personToBeDeleted;

    public static final String COMMAND_WORD = "deleteByName";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the CASE-SENSITIVE input.\n"
            + "Parameters: NAME\n"
            + "Example: " + COMMAND_WORD + " John";

    public static final String MESSAGE_DELETE_PERSON_BY_NAME_SUCCESS = "Deleted Person: %1$s";

    public DeleteByNameCommand(String toBeDeleted) {
        personToBeDeleted = toBeDeleted;
    }

    @Override
    public CommandResult execute() {
        try {
            boolean found = false;
            ReadOnlyPerson target = null;
            for (ReadOnlyPerson person : addressBook.getAllPersons()) {
                if (person.getName().toString().equals(personToBeDeleted)){
                    target = person;
                    found = true;
                    break;
                }
            }
            if (found){
                addressBook.removePerson(target);
                return new CommandResult(String.format(MESSAGE_DELETE_PERSON_BY_NAME_SUCCESS, target));
            }  else {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }

        }catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }
}
