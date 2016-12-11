package seedu.addressbook.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;

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
     * Returns true iff the underlying container behind an iterable is empty.
     */
    public static <T> boolean isEmpty(Iterable<T> it) {
        return !it.iterator().hasNext();
    }

    /**
     * Returns true iff the underlying containers behind two iterables are equal.
     */
    public static <T> boolean isEqual(Iterable<T> firstIterable, Iterable<T> secondIterable) {
        Iterator<T> currentPtr0 = firstIterable.iterator();
        Iterator<T> currentPtr1 = secondIterable.iterator();

        while (currentPtr0.hasNext()) {
            T val0 = currentPtr0.next();
            T val1 = currentPtr1.next();

            if (!val0.equals(val1)) {
                return false;
            }
        }

        return !currentPtr1.hasNext();
    }
}
