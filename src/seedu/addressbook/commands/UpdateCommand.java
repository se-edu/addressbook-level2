package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashSet;
import java.util.Set;

import static seedu.addressbook.commands.AddCommand.MESSAGE_DUPLICATE_PERSON;


/**
 * Deletes a person identified using it's last displayed index from the address book.
 */
public class UpdateCommand extends Command {

    public static final String COMMAND_WORD = "update";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates the person identified by the index number used in the last person listing with the details provided\n"
            + "Parameters: INDEX [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1"
            + " p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";


    public static final String MESSAGE_SUCCESS = "Details updated: %1$s";

    private final Person toAdd;

    public UpdateCommand(int targetVisibleIndex,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        super(targetVisibleIndex);

        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toAdd = new Person(
                new Name("temp name"),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                new UniqueTagList(tagSet)
        );
    }

    public void setName(){
        final ReadOnlyPerson target = getTargetPerson();
        Name name = target.getName();
        toAdd.rewriteName(name);
    }

    @Override
    public CommandResult execute() {
        try {
            setName();
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.removePerson(target);
            try {
                addressBook.addPerson(toAdd);
            } catch (UniquePersonList.DuplicatePersonException dpe) {
                return new CommandResult(MESSAGE_DUPLICATE_PERSON);
            }
            return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }

    }

}
