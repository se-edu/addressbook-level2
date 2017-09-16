package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.parser.Parser;
import seedu.addressbook.util.TestUtil;

import static org.junit.Assert.*;

public class TotalContactsCommandTest {
    private AddressBook addressBook = TestUtil.createAddressBook();
    private Person person = new Person(
            new seedu.addressbook.data.person.Name("John Doe"),
            new Phone("9876543", false),
            new Email("john@hotmail.com", false),
            new Address("blk 123, Singapore", false),
            new UniqueTagList()
    );

    public TotalContactsCommandTest() throws NullPointerException, IllegalValueException {
    }

    @Test
    public void noContacts() throws Exception {
        assertEquals(0, addressBook.getAllPersons().immutableListView().size());
    }

    @Test
    public void afterAddingOneContact() throws Exception {
        for (int i=0; i<1; i++) {
            addressBook.addPerson(person);
        }
        assertEquals(1, addressBook.getAllPersons().immutableListView().size());
    }

}