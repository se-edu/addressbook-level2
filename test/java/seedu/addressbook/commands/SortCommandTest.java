package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SortCommandTest {

    private AddressBook addressBook;

    private List<ReadOnlyPerson> sortedList;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());

        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);

        sortedList = TestUtil.createList(davidGrant, janeDoe, johnDoe, samDoe);

    }

    @Test
    public void execute_addressBookIsSorted() {
        assertSortSuccessful(addressBook, sortedList);
    }

    /**
            * Creates a new sort command.
            *
     */
    private SortCommand createSortCommand(AddressBook addressBook,
                                            List<ReadOnlyPerson> displayList) {

        SortCommand command = new SortCommand();
        command.setData(addressBook, displayList);

        return command;
    }
    /**
     * Asserts that the address book is successfully sorted.
     *
     * The addressBook passed in will not be modified (no side effects).
   */
    private void assertSortSuccessful(AddressBook addressBook,
                                      List<ReadOnlyPerson> displayList) {
        AddressBook expectedAddressBook = TestUtil.clone(addressBook);
        expectedAddressBook.sort();
        String expectedMessage = SortCommand.MESSAGE_SORTED;

        AddressBook actualAddressBook = TestUtil.clone(addressBook);

        SortCommand command = createSortCommand(actualAddressBook, displayList);
        assertCommandBehaviour(command, expectedMessage, expectedAddressBook, actualAddressBook);

    }

    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(SortCommand sortCommand, String expectedMessage,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = sortCommand.execute();

        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook.getAllPersons(), actualAddressBook.getAllPersons());
    }




}