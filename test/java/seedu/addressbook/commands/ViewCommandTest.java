package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Arrays;
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

public class ViewCommandTest {
    private AddressBook typicalAddressBook;
    private AddressBook emptyAddressBook;
    private List<ReadOnlyPerson> emptyDisplayList = Collections.emptyList();
    private List<ReadOnlyPerson> displayListWithAllTypicalPersons;
    private List<ReadOnlyPerson> listWithSome;

    @Before
    public void setUp() {
        emptyAddressBook = TestUtil.createAddressBook();

        TypicalPersons td = new TypicalPersons();
        typicalAddressBook = td.getTypicalAddressBook();
        displayListWithAllTypicalPersons = Arrays.asList(td.getTypicalPersons());
        listWithSome = Arrays.asList(td.amy, td.candy, td.dan);
    }

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        // empty addressbook
        assertViewErrorInvalidIndex(emptyAddressBook, emptyDisplayList, 1);

        // non-empty addressbook
        assertViewErrorInvalidIndex(typicalAddressBook, displayListWithAllTypicalPersons, -1);
        assertViewErrorInvalidIndex(typicalAddressBook, displayListWithAllTypicalPersons, 0);
        assertViewErrorInvalidIndex(typicalAddressBook, displayListWithAllTypicalPersons, displayListWithAllTypicalPersons.size() + 1);
    }

    @Test
    public void execute_personNotInAddressBook_returnsPersonNotInAddressBookMessage() throws Exception {
        // generate person not in addressbook, add to displayList
        ReadOnlyPerson someone = new Person(new Name("me"),
                                            new Phone("123", true),
                                            new Email("some@hey.go", true),
                                            new Address("nus", false),
                                            new UniqueTagList(Collections.emptySet()));
        List <ReadOnlyPerson> displayListWithExtraPerson = new ArrayList<ReadOnlyPerson>(displayListWithAllTypicalPersons);
        displayListWithExtraPerson.add(someone);

        // empty addressbook
        assertViewErrorPersonNotInAddressBook(emptyAddressBook, displayListWithExtraPerson, 1);

        // non-empty addressbook
        assertViewErrorPersonNotInAddressBook(typicalAddressBook, displayListWithExtraPerson, displayListWithExtraPerson.size());
    }

    @Test
    public void execute_validIndex_returnsPersonDetails() {
        // person with no private information
        assertViewSuccess(typicalAddressBook, displayListWithAllTypicalPersons, 1);

        // person with some private information
        assertViewSuccess(typicalAddressBook, displayListWithAllTypicalPersons, 2);

        // person with all private information
        assertViewSuccess(typicalAddressBook, displayListWithAllTypicalPersons, 4);

        // addressbook has more people than displayList
        // This can happen when view from filtered list caused by some commands(eg. FindCommand)
        assertViewSuccess(typicalAddressBook, listWithSome, 1);
    }

    private Command generateViewCommand(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons, int targetIndex) {
        Command command = new ViewCommand(targetIndex);
        command.setData(addressBook, relevantPersons);

        return command;
    }

    private Command generateViewAllCommand(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons, int targetIndex) {
        Command command = new ViewAllCommand(targetIndex);
        command.setData(addressBook, relevantPersons);

        return command;
    }

    /**
     * Asserts that both a ViewCommand and a ViewAllCommand can retrieve from
     * the {@code addressBook} details of the person at the given {@code index} in the given {@code list}.
     *
     * @param index one-indexed position of the target person in the list
     */
    private void assertViewSuccess(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        ReadOnlyPerson personToBeViewed = list.get(index - 1); // -1 is because index is one-indexed

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS,
                                               personToBeViewed.getAsTextHidePrivate());
        assertViewCommandBehaviour(addressBook, list, index, expectedMessage);

        expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS,
                                        personToBeViewed.getAsTextShowAll());
        assertViewAllCommandBehaviour(addressBook, list, index, expectedMessage);
    }

    /**
     * Asserts that the details of person at specific index cannot be retrieved due to
     * invalid index.
     */
    private void assertViewErrorInvalidIndex(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        assertCommandError(addressBook, list, index, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Asserts that the details of person at specific index cannot be retrieved due to
     * person not existing in the addressbook.
     */
    private void assertViewErrorPersonNotInAddressBook(AddressBook addressBook, List<ReadOnlyPerson> list, int index) {
        assertCommandError(addressBook, list, index, Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
    }

    /**
     * Asserts that the Viewcommand and ViewAllcommand reports the given error for the given input
     */
    private void assertCommandError(AddressBook addressBook, List<ReadOnlyPerson> list, int index,
                                                                            String expectedMessage) {
        assertViewCommandBehaviour(addressBook, list, index, expectedMessage);
        assertViewAllCommandBehaviour(addressBook, list, index, expectedMessage);
    }

    /**
     * Asserts that the View command gives given feedback information
     */
    private void assertViewCommandBehaviour(AddressBook addressBook, List<ReadOnlyPerson> list, int index, String expectedMessage) {
        Command command = generateViewCommand(addressBook, list, index);
        assertCommandResult(command, addressBook, TestUtil.clone(addressBook), expectedMessage);
    }

    /**
     * Asserts that the ViewAll command gives given feedback information
     */
    private void assertViewAllCommandBehaviour(AddressBook addressBook, List<ReadOnlyPerson> list, int index, String expectedMessage) {
        Command command = generateViewAllCommand(addressBook, list, index);
        assertCommandResult(command, addressBook, TestUtil.clone(addressBook), expectedMessage);
    }
}
