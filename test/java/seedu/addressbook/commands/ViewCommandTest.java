package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

public class ViewCommandTest {
    private TypicalPersons td = new TypicalPersons();

    private AddressBook typicalAddressBook = td.getTypicalAddressBook();
    private AddressBook emptyAddressBook = TestUtil.createAddressBook();
    private List<ReadOnlyPerson> emptyDisplayList = Collections.emptyList();
    private List<ReadOnlyPerson> displayListWithAllTypicalPersons = Arrays.asList(td.getTypicalPersons());
    private List<ReadOnlyPerson> listWithSome = Arrays.asList(td.amy, td.candy, td.dan);

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        // empty addressbook
        assertViewErrorInvalidIndex(emptyAddressBook, emptyDisplayList, 1);

        // non-empty addressbook
        assertViewErrorInvalidIndex(typicalAddressBook, displayListWithAllTypicalPersons, -1);
        assertViewErrorInvalidIndex(typicalAddressBook, displayListWithAllTypicalPersons, 0);
        assertViewErrorInvalidIndex(typicalAddressBook, displayListWithAllTypicalPersons,
                displayListWithAllTypicalPersons.size() + 1);
    }

    @Test
    public void execute_personNotInAddressBook_returnsPersonNotInAddressBookMessage() throws Exception {
        // generate person not in addressbook, add to displayList
        ReadOnlyPerson someone = new Person(new Name("me"),
                new Phone("123", true),
                new Email("some@hey.go", true),
                new Address("nus", false),
                new UniqueTagList(Collections.emptySet()));
        List <ReadOnlyPerson> displayListWithExtraPerson
                = new ArrayList<ReadOnlyPerson>(displayListWithAllTypicalPersons);
        displayListWithExtraPerson.add(someone);

        // empty addressbook
        assertViewErrorPersonNotInAddressBook(emptyAddressBook, displayListWithExtraPerson, 1);

        // non-empty addressbook
        assertViewErrorPersonNotInAddressBook(typicalAddressBook, displayListWithExtraPerson,
                displayListWithExtraPerson.size());
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

    /**
     * Asserts that the details of person at specific index cannot be retrieved due to
     * invalid index.
     */
    private void assertViewErrorInvalidIndex(AddressBook addressBook, List<ReadOnlyPerson> list,
                                             int targetVisibleIndex) {
        assertViewError(addressBook, list, targetVisibleIndex, Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Asserts that the details of person at specific index cannot be retrieved due to
     * person not existing in the addressbook.
     */
    private void assertViewErrorPersonNotInAddressBook(AddressBook addressBook, List<ReadOnlyPerson> list,
                                                       int targetVisibleIndex) {
        assertViewError(addressBook, list, targetVisibleIndex, Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
    }

    /**
     * Asserts that both a ViewCommand and a ViewAllCommand can retrieve from
     * the {@code addressBook} details of the person at the given {@code index} in the given {@code list}.
     *
     * @param targetVisibleIndex one-indexed position of the target person in the list
     */
    private void assertViewSuccess(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons,
                                   int targetVisibleIndex) {
        ReadOnlyPerson personToBeViewed = relevantPersons.get(targetVisibleIndex - 1); // -1 is because index is one-indexed

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS,
                                               personToBeViewed.getAsTextHidePrivate());
        assertViewBehavior(new ViewCommand(targetVisibleIndex), addressBook, relevantPersons, expectedMessage);

        expectedMessage = String.format(ViewAllCommand.MESSAGE_VIEW_PERSON_DETAILS,
                                        personToBeViewed.getAsTextShowAll());
        assertViewBehavior(new ViewAllCommand(targetVisibleIndex), addressBook, relevantPersons, expectedMessage);
    }

    /**
     * Asserts that the Viewcommand and ViewAllcommand reports the given error for the given input
     */
    private static void assertViewError(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons,
                                        int targetVisibleIndex, String expectedMessage) {
        assertViewBehavior(new ViewCommand(targetVisibleIndex), addressBook, relevantPersons, expectedMessage);
        assertViewBehavior(new ViewAllCommand(targetVisibleIndex), addressBook, relevantPersons, expectedMessage);
    }

    /**
     * Asserts that the ViewCommand and ViewAllCommand show given feedback information
     */
    private static void assertViewBehavior(Command viewCommand, AddressBook addressBook,
                                           List<ReadOnlyPerson> relevantPersons, String expectedMessage) {
        AddressBook expectedAddressBook = TestUtil.clone(addressBook);

        viewCommand.setData(addressBook, relevantPersons);
        CommandResult result = viewCommand.execute();

        // Feedback message is as expected and there are no relevant persons returned
        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(Optional.empty(), result.getRelevantPersons());

        // Address book was not modified
        assertEquals(expectedAddressBook.getAllPersons(), addressBook.getAllPersons());
    }

}
