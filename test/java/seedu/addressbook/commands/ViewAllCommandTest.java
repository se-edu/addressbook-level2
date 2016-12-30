package seedu.addressbook.commands;

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
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

import static seedu.addressbook.util.TestUtil.assertCommandResult;

public class ViewAllCommandTest {
    private AddressBook addressBook;
    private AddressBook emptyAddressBook;
    private List<ReadOnlyPerson> emptyDisplayList;
    private List<ReadOnlyPerson> listWithAll;
    private List<ReadOnlyPerson> listWithSome;

    @Before
    public void setUp() throws Exception {
        TypicalPersons td = new TypicalPersons();

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = td.getTypicalAddressBook();

        emptyDisplayList = TestUtil.createList();
        listWithAll = td.getListWithAllPersons();
        listWithSome = td.getListWithSomePersons();
    }

    @Test
    public void viewAllCommand_invalidIndex_returnsInvalidIndexMessage() {
        // empty addressbook
        assertViewAllErrorInvalidIndex(emptyAddressBook, emptyDisplayList, 1);

        // non-empty addressbook
        assertViewAllErrorInvalidIndex(addressBook, listWithAll, -1);
        assertViewAllErrorInvalidIndex(addressBook, listWithAll, 0);
        assertViewAllErrorInvalidIndex(addressBook, listWithAll, listWithAll.size() + 1);
    }

    @Test
    public void viewAllCommand_personNotInAddressBook_returnsPersonNotInAddressBookMessage() throws Exception {
        // generate person not in addressbook, add to displayList
        Person someone = new Person(new Name("me"),
                                    new Phone("123", true),
                                    new Email("some@hey.go", true),
                                    new Address("nus", false),
                                    new UniqueTagList(Collections.emptySet()));
        listWithAll.add(someone);

        // empty addressbook
        assertViewAllErrorPersonNotInAddressBook(emptyAddressBook, listWithAll, 1);

        // non-empty addressbook
        assertViewAllErrorPersonNotInAddressBook(addressBook, listWithAll, listWithAll.size());
    }

    @Test
    public void viewAllCommand_validIndex_returnsPersonDetails() {
        // person with no private information
        assertViewAllSuccess(addressBook, listWithAll, 1);

        // person with some private information
        assertViewAllSuccess(addressBook, listWithAll, 2);

        // addressbook has more people than displayList
        assertViewAllSuccess(addressBook, listWithSome, 1);
    }

    /**
     * Creates a new ViewAll command.
     */
    private Command generateViewAllCommand(AddressBook addressBook, List<ReadOnlyPerson> displayList, int index) {
        Command command = new ViewAllCommand(index);
        command.setData(addressBook, displayList);

        return command;
    }

    /**
     * Asserts that the details person at specific index can be successfully retrieved
     * and displayed.
     */
    private void assertViewAllSuccess(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        String expectedMessage = String.format(ViewAllCommand.MESSAGE_VIEW_PERSON_DETAILS,
                                               list.get(index - 1).getAsTextShowAll());

        Command command = generateViewAllCommand(addressBook, list, index);
        assertCommandResult(command, expectedMessage, addressBook, TestUtil.clone(addressBook));
    }

    /**
     * Asserts that the details of person at specific index cannot be retrieved due to
     * invalid index.
     */
    private void assertViewAllErrorInvalidIndex(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        String expectedMessage = Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

        Command command = generateViewAllCommand(addressBook, list, index);
        assertCommandResult(command, expectedMessage, addressBook, TestUtil.clone(addressBook));
    }

    /**
     * Asserts that the details of person at specific index cannot be retrieved due to
     * person not existing in the addressbook.
     */
    private void assertViewAllErrorPersonNotInAddressBook(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        String expectedMessage = Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK;

        Command command = generateViewAllCommand(addressBook, list, index);
        assertCommandResult(command, expectedMessage, addressBook, TestUtil.clone(addressBook));
    }
}
