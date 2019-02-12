package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

import java.util.HashSet;
import java.util.Set;


/**
 * Shows details of the person identified using the last displayed index.
 * Private contact details are not shown.
 */
public class ReviseCommand extends Command {

    public static final String COMMAND_WORD = "revise";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Revise details of the person "
            + "identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX [t/TAG] ...\n"
            + "Example: " + COMMAND_WORD + " 1 t/new_list";

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Revised person: %1$s";

    private final Set<Tag> replacement;

    /**
     * Convenience constructor using raw values.
     *
     * @throws IllegalValueException if any of the sets are invalid
     */
    public ReviseCommand(int targetVisibleIndex,
                         Set<String> tags) throws IllegalValueException {
        super(targetVisibleIndex);
        this.replacement = new HashSet<>();
        for (String tagName : tags) {
            this.replacement.add(new Tag(tagName));
        }
    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            if (!addressBook.containsPerson(target)) {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }
            ((Person) target).setTags(replacement);
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_DETAILS, target.getAsTextHidePrivate()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

}
