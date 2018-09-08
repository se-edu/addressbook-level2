package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {

    private AddressBook sortedAddressBook;
    private AddressBook addressBook;

    private List<ReadOnlyPerson> unsortedListWithEveryone;
    private List<ReadOnlyPerson> listWithSurnameDoe;

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

        sortedAddressBook = TestUtil.createAddressBook(davidGrant,janeDoe,johnDoe,samDoe);

        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);
        unsortedListWithEveryone = addressBook.getAllPersons().immutableListView();

    }

    @Test
    public void execute_Sorting() throws UniquePersonList.DuplicatePersonException {
        assertSortingSuccessful(sortedAddressBook, addressBook, unsortedListWithEveryone);
    }


    //Assert Sorting successful
    private void assertSortingSuccessful(AddressBook sortedAddressBook, AddressBook addressBook,
                                                      List<ReadOnlyPerson> unsortedListWithEveryone) throws UniquePersonList.DuplicatePersonException {

        SortCommand command = createSortCommand(addressBook, unsortedListWithEveryone);
        assertCommandBehaviour(command, sortedAddressBook, addressBook);
    }

    /**
     * Creates a new sort command.
     */
    private SortCommand createSortCommand(AddressBook addressBook,
                                          List<ReadOnlyPerson> displayList) {

        SortCommand command = new SortCommand();
        command.setData(addressBook, displayList);

        return command;
    }


    /**
     * Executes the command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehaviour(SortCommand sortCommand,
                                        AddressBook expectedAddressBook, AddressBook actualAddressBook) throws UniquePersonList.DuplicatePersonException {

        CommandResult result = sortCommand.execute();
        assertEquals(Optional.of(expectedAddressBook.getAllPersons().immutableListView()), result.getRelevantPersons());
    }

}