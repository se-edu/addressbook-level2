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
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

public class ViewCommandTest {
    private TypicalPersons td = new TypicalPersons();

    private AddressBook typicalAddressBook = td.getTypicalAddressBook();
    private AddressBook emptyAddressBook = TestUtil.createAddressBook();
    private List<ReadOnlyPerson> emptyPersonList = Collections.emptyList();
    private List<ReadOnlyPerson> listWithAllTypicalPersons = Arrays.asList(td.getTypicalPersons());
    private List<ReadOnlyPerson> listWithSomeTypicalPersons = Arrays.asList(td.amy, td.candy, td.dan);

    @Test
    public void execute_invalidIndex_returnsInvalidIndexMessage() {
        // empty addressbook
        assertViewErrorInvalidIndex(emptyAddressBook, emptyPersonList, 1);

        // non-empty addressbook
        assertViewErrorInvalidIndex(typicalAddressBook, listWithAllTypicalPersons, -1);
        assertViewErrorInvalidIndex(typicalAddressBook, listWithAllTypicalPersons, 0);
        assertViewErrorInvalidIndex(typicalAddressBook, listWithAllTypicalPersons,
                                              listWithAllTypicalPersons.size() + 1);
    }

    @Test
    public void execute_personNotInAddressBook_returnsPersonNotInAddressBookMessage() throws Exception {
        // generate list with person not in addressbook, add to list
        ReadOnlyPerson stranger = new Person(new Name("me"),
                                             new Phone("123", true),
                                             new Email("some@hey.go", true),
                                             new Address("nus", false),
                                             Collections.emptySet());
        List<ReadOnlyPerson> listWithExtraPerson
                = new ArrayList<ReadOnlyPerson>(listWithAllTypicalPersons);
        listWithExtraPerson.add(stranger);

        // empty addressbook
        assertViewErrorPersonNotInAddressBook(emptyAddressBook, listWithExtraPerson, 1);

        // non-empty addressbook
        assertViewErrorPersonNotInAddressBook(typicalAddressBook, listWithExtraPerson,
                                                            listWithExtraPerson.size());
    }

    @Test
    public void execute_validIndex_returnsPersonDetails() {
        // person with no private information
        assertViewSuccess(typicalAddressBook, listWithAllTypicalPersons, 1);

        // person with some private information
        assertViewSuccess(typicalAddressBook, listWithAllTypicalPersons, 2);

        // person with all private information
        assertViewSuccess(typicalAddressBook, listWithAllTypicalPersons, 4);

        // Addressbook has more people than the list.
        // This can happen when a command causes the list to show only a sub-set of persons(e.g. FindCommand).
        assertViewSuccess(typicalAddressBook, listWithSomeTypicalPersons, 1);
    }

    /**
     * Asserts that the details of person at specific index cannot be retrieved due to
     * invalid index.
     */
    private void assertViewErrorInvalidIndex(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons,
                                                                                     int targetVisibleIndex) {
        assertViewError(addressBook, relevantPersons, targetVisibleIndex,
                          Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
    }

    /**
     * Asserts that the details of person at specific index cannot be retrieved due to
     * person not existing in the addressbook.
     */
    private void assertViewErrorPersonNotInAddressBook(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons,
                                                                                               int targetVisibleIndex) {
        assertViewError(addressBook, relevantPersons, targetVisibleIndex,
                               Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
    }

    /**
     * Asserts that both a ViewCommand and a ViewAllCommand can retrieve from
     * the {@code addressBook} details of the person at the given {@code targetVisibleIndex}
     * in the given {@code relevantPersons} list.
     *
     * @param targetVisibleIndex one-indexed position of the target person in the list
     */
    private void assertViewSuccess(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons,
                                                                           int targetVisibleIndex) {
        // get person to be viewed (targetVisibleIndex - 1 because index is one-indexed)
        ReadOnlyPerson personToBeViewed = relevantPersons.get(targetVisibleIndex - 1);

        String expectedMessage = String.format(ViewCommand.MESSAGE_VIEW_PERSON_DETAILS,
                                                personToBeViewed.getAsTextHidePrivate());
        assertViewBehavior(new ViewCommand(targetVisibleIndex), addressBook, relevantPersons, expectedMessage);

        expectedMessage = String.format(ViewAllCommand.MESSAGE_VIEW_PERSON_DETAILS,
                                                personToBeViewed.getAsTextShowAll());
        assertViewBehavior(new ViewAllCommand(targetVisibleIndex), addressBook, relevantPersons, expectedMessage);
    }

    /**
     * Asserts that the Viewcommand and ViewAllcommand reports the given error for the given input.
     */
    private static void assertViewError(AddressBook addressBook, List<ReadOnlyPerson> relevantPersons,
                                                        int targetVisibleIndex, String expectedMessage) {
        assertViewBehavior(new ViewCommand(targetVisibleIndex), addressBook, relevantPersons, expectedMessage);
        assertViewBehavior(new ViewAllCommand(targetVisibleIndex), addressBook, relevantPersons, expectedMessage);
    }

    /**
     * Executes the test command for the given addressbook data.
     * Checks that ViewCommand and ViewAllCommand exhibits the correct command behavior, namely:
     * 1. The feedback message of the CommandResult it returns matches expectedMessage.
     * 2. The CommandResult it returns has no relevant persons.
     * 3. The original addressbook data is not modified after executing ViewCommand and ViewAllCommand.
     */
    private static void assertViewBehavior(Command viewCommand, AddressBook addressBook,
                                           List<ReadOnlyPerson> relevantPersons, String expectedMessage) {
        AddressBook expectedAddressBook = TestUtil.clone(addressBook);

        viewCommand.setData(addressBook, relevantPersons);
        CommandResult result = viewCommand.execute();

        // feedback message is as expected and there are no relevant persons returned.
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(Optional.empty(), result.getRelevantPersons());

        // addressbook was not modified.
        assertEquals(expectedAddressBook.getAllPersons(), addressBook.getAllPersons());
    }

}
