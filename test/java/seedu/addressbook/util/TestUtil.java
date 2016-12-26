package seedu.addressbook.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.tag.UniqueTagList;

public class TestUtil {
    /**
     * Creates an address book containing the given persons.
     * @throws DuplicatePersonException if two or more given persons are the same
     */
    public static AddressBook createAddressBook(Person... persons) throws DuplicatePersonException {
        AddressBook addressBook = new AddressBook();

        for (Person person : persons) {
            addressBook.addPerson(person);
        }

        return addressBook;
    }

    /**
     * Creates a list of persons.
     */
    public static List<ReadOnlyPerson> createList(Person...persons) {
        List<ReadOnlyPerson> list = new ArrayList<ReadOnlyPerson>();

        for (Person person : persons) {
            list.add(person);
        }

        return list;
    }

    /**
     * Creates a copy of the original address book with the same entries
     * of Persons and Tags. The Persons and Tags are not cloned.
     */
    public static AddressBook clone(AddressBook addressBook) {
        return new AddressBook(addressBook.getAllPersons(), addressBook.getAllTags());
    }
    public static Person generateTestPerson() {
        try {
            return new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, false),
                    new Email(Email.EXAMPLE, true), new Address(Address.EXAMPLE, false), new UniqueTagList());
        } catch (IllegalValueException e) {
            fail("test person data should be valid by definition");
            return null;
        }
    }
    
    public static void compareFiles(Path path1, Path path2) throws IOException {
        Scanner scan1 = new Scanner(path1);
        Scanner scan2 = new Scanner(path2);
        while (scan1.hasNext() && scan2.hasNext()) {
            assertEquals(scan1.nextLine(), scan2.nextLine());
        }

        //ensure no additional lines for both files
        assertFalse(scan1.hasNext() || scan2.hasNext());
        scan1.close();
        scan2.close();
    }
}
