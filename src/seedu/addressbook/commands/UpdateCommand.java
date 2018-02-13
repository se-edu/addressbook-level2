package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.ui.TextUi;

import java.util.HashSet;
import java.util.Set;

import static seedu.addressbook.commands.AddCommand.MESSAGE_DUPLICATE_PERSON;


/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates information of the person identified by the index number used in the last shown person listing.\n"
            + "Parameters: INDEX NAME p/(new)PHONE [p]e/(new)EMAIL [p]a/(new)ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_UPDATE_PERSON_SUCCESS = "Person updated: %1$s";

    private final Person toAdd;



    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public UpdateCommand(String index, String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
        this.targetIndex = Integer.parseInt(index);
    }


    @Override
    public CommandResult execute() {
        try {
            ReadOnlyPerson target = addressBook.getAllPersons().immutableListView().get(this.targetIndex-TextUi
                    .DISPLAYED_INDEX_OFFSET);
            toAdd.setRating(target.getRating());
            addressBook.setPerson(this.targetIndex- TextUi.DISPLAYED_INDEX_OFFSET, toAdd);
            return new CommandResult(String.format(MESSAGE_UPDATE_PERSON_SUCCESS, toAdd));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }

}
