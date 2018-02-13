package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.ui.TextUi;

import java.util.HashSet;
import java.util.Set;

import static seedu.addressbook.commands.AddCommand.MESSAGE_DUPLICATE_PERSON;


/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class RateCommand extends Command {

    public static final String COMMAND_WORD = "rate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Rate a person identified by the index number used in the last shown person listing.\n"
            + "Parameters: INDEX RATING"
            + "Example: " + COMMAND_WORD + " 1 5";

    public static final String MESSAGE_UPDATE_PERSON_SUCCESS = "Person rated: %1$s";

    private final int rating;



    /**
     * Convenience constructor using raw values.
     */
    public RateCommand(int index, int rating) {
        this.rating = rating;
        this.targetIndex = index;
    }


    @Override
    public CommandResult execute() {
        try {
            ReadOnlyPerson target = addressBook.getAllPersons().immutableListView().get(this.targetIndex-TextUi
                    .DISPLAYED_INDEX_OFFSET);
            Person ratedTarget = new Person(target);
            ratedTarget.setRating(rating);
            addressBook.setPerson(this.targetIndex- TextUi.DISPLAYED_INDEX_OFFSET, ratedTarget);
            return new CommandResult(String.format(MESSAGE_UPDATE_PERSON_SUCCESS, ratedTarget));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }

}
