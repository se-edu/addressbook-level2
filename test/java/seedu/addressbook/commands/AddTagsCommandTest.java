package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.util.TestUtil;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class AddTagsCommandTest {
    private AddressBook addressBook;
    private AddressBook emptyAddressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(
                new Name("John Doe"),
                new Phone("61234567", false),
                new Email("john@doe.com", false),
                new Address("395C Ben Road", false),
                new UniqueTagList());
        Person janeDoe = new Person(
                new Name("Jane Doe"),
                new Phone("61234567", false),
                new Email("jane@doe.com", false),
                new Address("395C Ben Road", false),
                new UniqueTagList());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe);

        emptyDisplayList = TestUtil.createList();
        listWithEveryone = TestUtil.createList(johnDoe, janeDoe);
    }

    @Test
    public void addTagsCommand_invalidTags_throwsException() {
        final String[][] invalidTags = { { "" }, { " " }, { "'" }, { "[]\\[;]" }, { "validTag", "" },
                { "", " " } };
        for (String[] tags : invalidTags) {
            Set<String> tagsToAdd = new HashSet<>(Arrays.asList(tags));
            try {
                new AddTagsCommand(1, tagsToAdd);
            } catch (IllegalValueException e) {
                return;
            }
            String error = String.format(
                    "An add tags command was successfully constructed with invalid tags: %s", tagsToAdd);
            fail(error);
        }
    }

    @Test
    public void execute_personNotInAddressBook_returnsPersonNotFoundMessage() throws IllegalValueException {
        String[] tagsArray = new String[] { "friend", "colleague" };
        Set<String> tagsToAdd = new HashSet<>(Arrays.asList(tagsArray));
        AddTagsCommand command = new AddTagsCommand(1, tagsToAdd);
        command.setData(emptyAddressBook, listWithEveryone);

        CommandResult result = command.execute();

        String expectedMessage = Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;
        assertEquals(result.feedbackToUser, expectedMessage);
    }

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() throws IllegalValueException {
        String[] tagsArray = new String[] { "friend", "colleague" };
        Set<String> tagsToAdd = new HashSet<>(Arrays.asList(tagsArray));
        AddTagsCommand command = new AddTagsCommand(1, tagsToAdd);
        command.setData(addressBook, emptyDisplayList);

        CommandResult result = command.execute();

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        assertEquals(result.feedbackToUser, expectedMessage);
    }

    @Test
    public void execute_validIndex_tagsAddedToPerson() throws IllegalValueException {
        String[] tagsArray = new String[] { "friend", "colleague" };
        Set<String> tagsToAdd = new HashSet<>(Arrays.asList(tagsArray));
        AddTagsCommand command = new AddTagsCommand(1, tagsToAdd);
        command.setData(addressBook, listWithEveryone);

        CommandResult result = command.execute();
        ReadOnlyPerson addTagsPerson = listWithEveryone.get(0);

        String expectedMessage = String.format(AddTagsCommand.MESSAGE_ADD_TAGS_SUCCESS, addTagsPerson);
        assertEquals(result.feedbackToUser, expectedMessage);

        Set<Tag> expectedTagSet = new HashSet<>();
        for (String tagName : tagsToAdd) {
            expectedTagSet.add(new Tag(tagName));
        }
        UniqueTagList expectedTags = new UniqueTagList(expectedTagSet);
        assertEquals(addTagsPerson.getTags(), expectedTags);
    }
}
