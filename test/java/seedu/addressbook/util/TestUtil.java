package seedu.addressbook.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;

public class TestUtil {
    /**
     * Creates an address book containing the given persons.
     */
    public static AddressBook createAddressBook(Person... persons) {
        AddressBook addressBook = new AddressBook();

        for (Person person : persons) {
            try {
                addressBook.addPerson(person);
            } catch (DuplicatePersonException e) {
                throw new AssertionError(e);
            }
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
        return new AddressBook(addressBook.getAllPersons());
    }

    /**
     * Returns true if every pair of corresponding elements two iterables are (deeply) identical.
     * In other words, the two containers must have the same elements, in the same order.
     */
    public static <T> boolean isIdentical(Iterable<T> firstIterable, Iterable<T> secondIterable) {
        Iterator<T> currentPtr0 = firstIterable.iterator();
        Iterator<T> currentPtr1 = secondIterable.iterator();

        while (currentPtr0.hasNext() && currentPtr1.hasNext()) {
            T val0 = currentPtr0.next();
            T val1 = currentPtr1.next();

            if (!val0.equals(val1)) {
                return false;
            }
        }

        // If any of the two iterables still have elements, then they have different sizes.
        return !(currentPtr0.hasNext() || currentPtr1.hasNext());
    }

    /**
     * Returns true if the underlying container behind an iterable is empty.
     */
    public static <T> boolean isEmpty(Iterable<T> it) {
        return !it.iterator().hasNext();
    }

    /**
     * Returns the number of elements in the container behind an iterable.
     */
    public static <T> int getSize(Iterable<T> it) {
        int numberOfElementsSeen = 0;

        for (T elem : it) {
            numberOfElementsSeen++;
        }

        return numberOfElementsSeen;
    }

    public static Person generateTestPerson() {
        try {
            return new Person(new Name(Name.EXAMPLE), new Phone(Phone.EXAMPLE, false),
                    new Email(Email.EXAMPLE, true), new Address(Address.EXAMPLE, false), Collections.emptySet());
        } catch (IllegalValueException e) {
            fail("test person data should be valid by definition");
            return null;
        }
    }

    /**
     * Asserts whether the text in the two given files are the same. Ignores any
     * differences in line endings
     */
    public static void assertTextFilesEqual(Path path1, Path path2) throws IOException {
        List<String> list1 = Files.readAllLines(path1, Charset.defaultCharset());
        List<String> list2 = Files.readAllLines(path2, Charset.defaultCharset());
        assertEquals(String.join("\n", list1), String.join("\n", list2));
    }

    /**
     * Asserts that the file given does not exist on the filesystem.
     */
    public static void assertFileDoesNotExist(String filePath) {
        assertTrue(Files.notExists(Paths.get(filePath)));
    }
}
