package seedu.addressbook.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.*;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.util.TestUtil;

public class SortCommandTest {
    private AddressBook addressBook;
    private AddressBook sortedAddressBook;
    
    @Before
    public void setUp() throws Exception {
        Person abi = new Person(new Name("Abi Doe"), new Phone("61234567", false),
                new Email("john@doe.com", false), new Address("395C Ben Road", false), Collections.emptySet());
        Person ben = new Person(new Name("Ben Doe"), new Phone("91234567", false),
                new Email("jane@doe.com", false), new Address("33G Ohm Road", false), Collections.emptySet());
        Person charlie = new Person(new Name("Charlie Doe"), new Phone("63345566", false),
                new Email("sam@doe.com", false), new Address("55G Abc Road", false), Collections.emptySet());
        Person david = new Person(new Name("David Grant"), new Phone("61121122", false),
                new Email("david@grant.com", false), new Address("44H Define Road", false),
                Collections.emptySet());

        sortedAddressBook= TestUtil.createAddressBook(abi,ben,charlie,david);
        addressBook = TestUtil.createAddressBook(ben,abi,david,charlie);
    }

    @Test
    public void sortCommand_sortedData_correctlySorted(){
        assertFalse(addressBook.equals(sortedAddressBook));
        addressBook.sort();
        assertTrue(addressBook.equals(sortedAddressBook));
    }
}
