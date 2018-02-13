package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {
    private AddressBook addressBook;

    private Command sortCommand;

    private List<ReadOnlyPerson> expectedList;
    private List<ReadOnlyPerson> outputAllPersons;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), new UniqueTagList());
        Person janeDoe = new Person(new Name("Jane Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), new UniqueTagList());
        Person samDoe = new Person(new Name("Sam Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), new UniqueTagList());
        Person davidGrant = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                new UniqueTagList());

        addressBook = TestUtil.createAddressBook(johnDoe, janeDoe, davidGrant, samDoe);
        expectedList = TestUtil.createList(davidGrant, janeDoe, johnDoe, samDoe);
    }

    @Test
    public void execute() {
        sortCommand = new SortCommand();
        sortCommand.setData(addressBook, Collections.emptyList());
        assertSortSuccessful(expectedList);
    }

    private void assertSortSuccessful(List<ReadOnlyPerson> expectedList) {
        CommandResult result = sortCommand.execute();
        assertEquals(expectedList, result.getRelevantPersons().get());
    }
}
