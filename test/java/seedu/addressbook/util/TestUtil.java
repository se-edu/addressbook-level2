package seedu.addressbook.util;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.tag.Tag;
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

    /**
     * Returns true iff every pair of corresponding elements two iterables are (deeply) identical.
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
     * Returns true iff the underlying container behind an iterable is empty.
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
                    new Email(Email.EXAMPLE, true), new Address(Address.EXAMPLE, false), new UniqueTagList());
        } catch (IllegalValueException e) {
            fail("test person data should be valid by definition");
            return null;
        }
    }
    
    public static UniqueTagList getAllTags(UniquePersonList persons) {
        Set<Tag> combinedTagList = new HashSet<Tag>();
        
        for (Person person : persons) {
            for (Tag tag : person.getTags()) {
                combinedTagList.add(tag);
            }
        }
        
        return new UniqueTagList(combinedTagList);
    }
}
