package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

import java.util.HashSet;
import java.util.Set;

public class AddTagsCommand extends Command {
    public static final String COMMAND_WORD = "addTags";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds the given tags to the person identified by the index number used in the last person listing.\n"
            + "Parameters: INDEX [t/TAG]...\n"
            + "Example: " + COMMAND_WORD + " 1 t/ friend t/ colleague";
    public static final String MESSAGE_ADD_TAGS_SUCCESS = "Tags added to person: %1$s";

    private final UniqueTagList tagList;

    public AddTagsCommand(int targetVisibleIndex, Set<String> tags) throws IllegalValueException {
        super(targetVisibleIndex);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(new Tag(tagName));
        }
        this.tagList = new UniqueTagList(tagSet);
    }

    public UniqueTagList getTagList() {
        return tagList;
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.addTagsToPerson(target, this.tagList);
            return new CommandResult(String.format(MESSAGE_ADD_TAGS_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.PersonNotFoundException e) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        }
    }
}
