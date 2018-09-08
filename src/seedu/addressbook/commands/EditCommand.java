package seedu.addressbook.commands;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;

import java.util.HashSet;
import java.util.Set;

/**
 * Edits a person identified using it's last displayed index from the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Edits the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 Kristo p/123456";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toEdit;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the raw values are invalid
     */
    public EditCommand(String name,
                      String phone, boolean isPhonePrivate,
                      String email, boolean isEmailPrivate,
                      String address, boolean isAddressPrivate,
                      Set<String> tags) throws IllegalValueException {
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.toEdit = new Person(
                new Name(name),
                new Phone(phone, isPhonePrivate),
                new Email(email, isEmailPrivate),
                new Address(address, isAddressPrivate),
                tagSet
        );
    }

    public EditCommand(Person toEdit) {
        this.toEdit = toEdit;
    }

    public ReadOnlyPerson getPerson() {
        return toEdit;
    }

    @Override
    public CommandResult execute() {
        try {
            addressBook.addPerson(toEdit);
            return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, toEdit));
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return new CommandResult(MESSAGE_DUPLICATE_PERSON);
        }
    }
}
