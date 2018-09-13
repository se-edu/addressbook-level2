package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


/**
 * Deletes people identified using their last displayed indices from the address book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes people identified using their last displayed indices from the last person listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 2 3";

    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "Deleted Person(s): %1$s %2$s %3$s";
    private final List<Integer> targetVisibleIndices;


    public DeleteCommand(Set<Integer> targetVisibleIndices) {
        this.targetVisibleIndices = new ArrayList<>();
        for (Integer targetIndex : targetVisibleIndices) {
            this.targetVisibleIndices.add(targetIndex);
        }
    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            return new CommandResult(String.format(MESSAGE_DELETE_PEOPLE_SUCCESS, target));

        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

}
