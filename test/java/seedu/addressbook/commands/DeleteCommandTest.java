package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

public class DeleteCommandTest {

    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> listWithSurnameDoe;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), new UniqueTagList());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), new UniqueTagList());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                new UniqueTagList());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant);

        emptyDisplayList = TestUtil.createList();
        
        listWithEveryone = TestUtil.createList(johnDoe, janeDoe, davidGrant);
        listWithSurnameDoe = TestUtil.createList(johnDoe, janeDoe);
    }

    @Test
    public void execute_emptyAddressBook_returnsPersonNotFoundMessage() {
        DeleteCommand command = createDeleteCommand(1, emptyAddressBook, listWithEveryone);

        assertCommandBehaviour(command, Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK, emptyAddressBook,
                emptyAddressBook);
    }

    @Test
    public void execute_noPersonDisplayed_returnsInvalidIndexMessage() {
        assertInvalidIndex(1, addressBook, emptyDisplayList);
    }

    @Test
    public void execute_targetPersonNotInAddressBook_returnsPersonNotFoundMessage()
            throws IllegalValueException {
        Person notInAddressBookPerson = new Person(new Name("Not In Book"), new Phone("63331444", false),
                new Email("notin@book.com", false), new Address("156D Grant Road", false), new UniqueTagList());
        List<ReadOnlyPerson> listWithPersonNotInAddressBook = TestUtil.createList(notInAddressBookPerson);

        DeleteCommand command = createDeleteCommand(1, addressBook, listWithPersonNotInAddressBook);

        assertCommandBehaviour(command, Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK, addressBook, addressBook);
    }

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        assertInvalidIndex(0, addressBook, listWithEveryone);
        assertInvalidIndex(-1, addressBook, listWithEveryone);
        assertInvalidIndex(listWithEveryone.size() + 1, addressBook, listWithEveryone);
    }

    @Test
    public void execute_validIndex_personIsDeleted() throws PersonNotFoundException {
        final int targetPersonIndex = 0;

        ReadOnlyPerson targetPerson = listWithSurnameDoe.get(targetPersonIndex);
        AddressBook expectedAddressBook = new AddressBook(addressBook.getAllPersons(),
                addressBook.getAllTags());
        expectedAddressBook.removePerson(targetPerson);

        DeleteCommand command = createDeleteCommand(targetPersonIndex + TextUi.DISPLAYED_INDEX_OFFSET,
                addressBook, listWithSurnameDoe);

        assertCommandBehaviour(command,
                String.format(DeleteCommand.MESSAGE_DELETE_PERSON_SUCCESS, targetPerson), expectedAddressBook,
                addressBook);
    }

    /**
     * Creates a new delete command.
     * 
     * @param targetVisibleIndex of the person that we want to delete
     * @param addressBook to perform the delete operation on, must not be null
     * @param displayList to get the selected person to delete, must not be null
     */
    public DeleteCommand createDeleteCommand(int targetVisibleIndex, AddressBook addressBook,
            List<ReadOnlyPerson> displayList) {

        assert addressBook != null;
        assert displayList != null;

        DeleteCommand command = new DeleteCommand(targetVisibleIndex);
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    public void assertCommandBehaviour(DeleteCommand deleteCommand, String expectedMessage,
            AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = deleteCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }
    
    /**
     * Given an invalid index, checks that the command reports the invalidity of such index.
     * 
     * @param addressBook to perform the delete operation on, must not be null
     * @param displayList to get the selected person to delete, must not be null
     */
    public void assertInvalidIndex(int invalidIndex, AddressBook addressBook, 
            List<ReadOnlyPerson> displayList) {

        assert addressBook != null;
        assert displayList != null;
        
        DeleteCommand command = createDeleteCommand(invalidIndex, addressBook, displayList);

        assertCommandBehaviour(command, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX, addressBook,
                addressBook);
    }
}
