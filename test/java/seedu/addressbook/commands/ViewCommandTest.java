package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.util.TestUtil;

public class ViewCommandTest {
    private AddressBook addressBook;
    private AddressBook backUpAddressBook;
    private AddressBook emptyAddressBook;
    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithAll;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"),
                                    new Phone("98765432", false),
                                    new Email("johnd@gmail.com", false),
                                    new Address("John street, block 123, #01-01", false),
                                    new UniqueTagList(Collections.emptySet()));
        Person betsyCrowe = new Person(new Name("Betsy Crowe"),
                                       new Phone("1234567", true),
                                       new Email("betsycrowe@gmail.com", false),
                                       new Address("Newgate Prison", true),
                                       new UniqueTagList(new Tag("friend"), new Tag("criminal")));

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, betsyCrowe);
        
        emptyDisplayList = TestUtil.createList();
        listWithAll = TestUtil.createList(johnDoe, betsyCrowe);
        TestUtil.createList(johnDoe);
    }

    @Test
    public void viewCommand_invalidIndex_returnsInvalidIndexMessage() {
        // empty addressbook
        backUpAddressBook = TestUtil.clone(emptyAddressBook);
        assertViewErrorInvalidIndex(emptyAddressBook, emptyDisplayList, 1);

        // non-empty addressbook
        backUpAddressBook = TestUtil.clone(addressBook);
        assertViewErrorInvalidIndex(addressBook, listWithAll, -1);
        assertViewErrorInvalidIndex(addressBook, listWithAll, 0);
        assertViewErrorInvalidIndex(addressBook, listWithAll, 3);
    }

    @Test
    public void viewCommand_personNotInAddressBook_returnsPersonNotInAddressBookMessage() throws Exception {
        // person not in addressbook
        Person someone = new Person(new Name("me"),
                                    new Phone("123", true),
                                    new Email("some@hey.go", true),
                                    new Address("nus", false),
                                    new UniqueTagList(Collections.emptySet()));
        listWithAll.add(someone);

        // empty addressbook
        backUpAddressBook = TestUtil.clone(emptyAddressBook);
        assertViewErrorPersonNotInAddressBook(emptyAddressBook, listWithAll, 1);

        // non-empty addressbook
        backUpAddressBook = TestUtil.clone(addressBook);
        assertViewErrorPersonNotInAddressBook(addressBook, listWithAll, 3);
    }

    @Test
    public void viewCommand_validIndex_returnsPersonDetails() {
        backUpAddressBook = TestUtil.clone(addressBook);
        // person with no private information
        assertViewSuccess(addressBook, listWithAll, 1);
        
        // person with some private information
        assertViewSuccess(addressBook, listWithAll, 2);

    }

    /**
     * Creates a new view command.
     *
     * @param targetVisibleIndex of the person
     */
    private Command generateViewCommand(AddressBook addressBook, List<ReadOnlyPerson> displayList, int index) {

        ViewCommand command = new ViewCommand(index);
        command.setData(addressBook, displayList);

        return command;
    }

    private void assertViewSuccess(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS, list.get(index - 1).getAsTextHidePrivate());

        Command command = generateViewCommand(addressBook, list, index);
        assertCommandResult(command, expectedMessage, addressBook);
    }

    private void assertViewErrorInvalidIndex(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        Command command = generateViewCommand(addressBook, list, index);
        assertCommandResult(command, expectedMessage, addressBook);
    }

    private void assertViewErrorPersonNotInAddressBook(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        String expectedMessage = Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;

        Command command = generateViewCommand(addressBook, list, index);
        assertCommandResult(command, expectedMessage, addressBook);
    }

    /**
     * Executes the command, and asserts the result message is as expected.
     */
    private void assertCommandResult(Command command, String expectedMessage,
                                        AddressBook addressBook) {
        CommandResult result = command.execute();

        // asserts the result message is correct as expected
        assertEquals(expectedMessage, result.feedbackToUser);

        // asserts the view command does not mutate the data
        // TODO: overwrite equals method in AddressBook and replace with equals method below 
        assertEquals(addressBook.getAllPersons(), backUpAddressBook.getAllPersons());
    }
}
