package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
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

    public static final String MESSAGE_DELETE_PEOPLE_SUCCESS = "Deleted Person(s): ";
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
            List<ReadOnlyPerson> people = new ArrayList<ReadOnlyPerson>();
            for (Integer targetIndex : targetVisibleIndices) {
              setTargetIndex(targetIndex);
              final ReadOnlyPerson target = getTargetPerson();
              people.add(target);
            }
            addressBook.removePeople(people);
            String commandResultString = MESSAGE_DELETE_PEOPLE_SUCCESS + Arrays.toString(targetVisibleIndices.toArray());
            commandResultString = commandResultString.replaceAll("[\\[\\]]","");
            return new CommandResult(commandResultString);
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }

}
