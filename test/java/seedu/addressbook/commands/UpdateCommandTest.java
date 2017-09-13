package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.List;


import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.ui.TextUi;
import seedu.addressbook.util.TestUtil;

public class UpdateCommandTest {
    private AddressBook emptyAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithEveryone;
    private List<ReadOnlyPerson> listWithSurnameDoe;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), new UniqueTagList());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), new UniqueTagList());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, samDoe);

        emptyDisplayList = TestUtil.createList();

        listWithEveryone = TestUtil.createList(johnDoe, samDoe);
        listWithSurnameDoe = TestUtil.createList(johnDoe, samDoe);
    }



    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        assertUpdateFailsDueToInvalidIndex(0, addressBook, listWithEveryone);
        assertUpdateFailsDueToInvalidIndex(-1, addressBook, listWithEveryone);
    }

    /**
     * Creates a new update command.
     *
     * @param targetVisibleIndex of the person that we want to delete
     */
    private UpdateCommand createUpdateCommand(int targetVisibleIndex, AddressBook addressBook,
                                              List<ReadOnlyPerson> displayList) {

        UpdateCommand command = new UpdateCommand(targetVisibleIndex);
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(UpdateCommand updateCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = updateCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }

    /**
     * Asserts that the index is not valid for the given display list.
     */
    private void assertUpdateFailsDueToInvalidIndex(int invalidVisibleIndex, AddressBook addressBook,
                                                    List<ReadOnlyPerson> displayList) {

        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        UpdateCommand command = createUpdateCommand(invalidVisibleIndex, addressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, addressBook, addressBook);
    }

}



