package seedu.addressbook.commands;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {

    private AddressBook emptyAddressBook;
    private AddressBook addressBook;
    private AddressBook expectedAddressBook;

    @Before
    public void setUp() throws Exception {
        Person johnDoe = new Person(new Name("John Doe"), new Phone("123", false),
                new Email("John@gmail.com", false), new Address("John Road", false), Collections.emptySet());
        Person peterQuill = new Person(new Name("Peter Quill"), new Phone("123", false),
                new Email("Peter@gmail.com", false), new Address("Star Road", false), Collections.emptySet());
        Person andyTan = new Person(new Name("Andy Tan"), new Phone("123", false),
                new Email("Andy@gmail.com", false), new Address("Kent Ridge Drive", false), Collections.emptySet());

        emptyAddressBook = TestUtil.createAddressBook();
        addressBook = TestUtil.createAddressBook(johnDoe, peterQuill, andyTan);

        expectedAddressBook = TestUtil.createAddressBook(andyTan, johnDoe, peterQuill);
    }

    @Test
    public void testSort(){
        addressBook.sort();
        assertEquals(expectedAddressBook.getAllPersons(), addressBook.getAllPersons());
    }

}