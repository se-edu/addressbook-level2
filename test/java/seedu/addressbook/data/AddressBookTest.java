package seedu.addressbook.data;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;
import seedu.addressbook.data.tag.UniqueTagList.TagNotFoundException;

public class AddressBookTest {
    private Tag tagPrizeWinner;
    private Tag tagScientist;
    private Tag tagMathematician;
    private Tag tagEconomist;

    private Person personAliceBetsy; // has tagMathematician
    private Person personBobChaplin; // has tagMathematician
    private Person personCharlieDouglas; // has tagScientist
    private Person personDavidElliot; // has tagEconomist, tagPrizeWinner

    /**
     * Initialises an AddressBook with some default values.
     * 
     * Contains personAliceBetsy and personBobChaplin.
     * Contains used tag tagMathematician and unused tag tagScientist.
     * Does not contain tagEconomist.
     * 
     * @return the initialised AddressBook
     * @throws Exception
     */
    private AddressBook createDefaultAddressBook() {
        UniqueTagList tags;
        UniquePersonList persons;
        AddressBook addressBook;
        
        try {
            tags = new UniqueTagList(tagMathematician, tagScientist);
            persons = new UniquePersonList(personAliceBetsy, personBobChaplin);
            addressBook = new AddressBook(persons, tags);    
        } catch (DuplicateTagException | DuplicatePersonException e) {
            // The choice of Tag and Person values are such that this method never throws.
            // We should thus never reach this point.
            throw new RuntimeException();
        }

        return addressBook;
    }

    /**
     * Checks if the underlying container behind an iterable is empty.
     * 
     * @param it, the iterable
     * @return whether the container is empty
     */
    private <T> boolean isEmpty(Iterable<T> it) {
        return !it.iterator().hasNext();
    }

    /**
     * Checks if the underlying containers behind two iterables are equal.
     * 
     * @param it0, the iterable representing the first container
     * @param it1, the iterable representing the second container
     * @return if the underlying containers are equal
     */
    private <T> boolean isEqual(Iterable<T> it0, Iterable<T> it1) {
        Iterator<T> currentPtr0 = it0.iterator();
        Iterator<T> currentPtr1 = it1.iterator();

        while (currentPtr0.hasNext()) {
            T val0 = currentPtr0.next();
            T val1 = currentPtr1.next();

            if (!val0.equals(val1)) {
                return false;
            }
        }

        return !currentPtr1.hasNext();
    }

    @Before
    public void setUp() throws Exception {
        // Tag constructor will not throw as the strings supplied match the
        // regex \p{Alnum}+
        tagPrizeWinner   = new Tag("prizewinner");
        tagScientist     = new Tag("scientist");
        tagMathematician = new Tag("mathematician");
        tagEconomist     = new Tag("economist");

        // UniqueTagList constructor will not throw as there are no duplicate
        // elements
        personAliceBetsy     = new Person(new Name("Alice Betsy"),
                                          new Phone("91235468", false),
                                          new Email("alice@nushackers.org", false),
                                          new Address("8 Computing Drive, Singapore", false),
                                          new UniqueTagList(tagMathematician));

        personBobChaplin     = new Person(new Name("Bob Chaplin"),
                                          new Phone("94321500", false),
                                          new Email("bob@nusgreyhats.org", false),
                                          new Address("9 Computing Drive", false),
                                          new UniqueTagList(tagMathematician));

        personCharlieDouglas = new Person(new Name("Charlie Douglas"),
                                          new Phone("98751365", false),
                                          new Email("charlie@nusgdg.org", false),
                                          new Address("10 Science Drive", false),
                                          new UniqueTagList(tagScientist));

        personDavidElliot    = new Person(new Name("David Elliot"),
                                          new Phone("84512575", false),
                                          new Email("douglas@nuscomputing.com", false),
                                          new Address("11 Arts Link", false),
                                          new UniqueTagList(tagEconomist, tagPrizeWinner));
    }

    @Test
    public void sync_emptyInitialTagList_addsAllIntoTagList() throws Exception {
        UniqueTagList emptyTagList = new UniqueTagList();
        UniquePersonList persons = new UniquePersonList(personBobChaplin, personCharlieDouglas);

        AddressBook addressBook = new AddressBook(persons, emptyTagList);

        assertTrue(addressBook.containsTag(tagMathematician));
        assertTrue(addressBook.containsTag(tagScientist));
        assertFalse(addressBook.containsTag(tagEconomist));

    }

    @Test
    public void sync_addNewElementWhoseTagsNotInTagList_addsMissingTagsIntoTagList() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertFalse(addressBook.containsTag(tagEconomist));
        assertFalse(addressBook.containsTag(tagPrizeWinner));
        addressBook.addPerson(personDavidElliot);
        assertTrue(addressBook.containsTag(tagEconomist));
        assertTrue(addressBook.containsTag(tagPrizeWinner));
    }

    @Test
    public void sync_defaultAddressBook_tagEqualityPropertyHolds() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        UniquePersonList persons = addressBook.getAllPersons();
        UniqueTagList tags = addressBook.getAllTags();

        boolean allPersonTagsValid = true; // all person tags are equal to some
                                           // tag in master list
        for (Person person : persons) {
            for (Tag personTag : person.getTags()) {
                boolean personTagValid = false; // particular person tag is
                                                // equal to some tag in master
                                                // list

                for (Tag tag : tags) {
                    personTagValid = personTagValid || (tag == personTag);
                }

                allPersonTagsValid = allPersonTagsValid && personTagValid;
            }
        }

        assertTrue(allPersonTagsValid);
    }

    @Test
    public void addPerson_addNonDuplicateWhoseTagInTagList_addsNormally() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertFalse(addressBook.containsPerson(personCharlieDouglas));
        addressBook.addPerson(personCharlieDouglas);
        assertTrue(addressBook.containsPerson(personCharlieDouglas));
    }

    @Test
    public void addPerson_addNonDuplicateWhoseTagNotInTagList_addsNormally() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertFalse(addressBook.containsPerson(personDavidElliot));
        addressBook.addPerson(personDavidElliot);
        assertTrue(addressBook.containsPerson(personDavidElliot));
    }

    @Test
    public void addPerson_addDuplicate_throwsDuplicatePersonException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean expectedExceptionCaught = false;

        try {
            addressBook.addPerson(personAliceBetsy);
        } catch (DuplicatePersonException e) {
            expectedExceptionCaught = true;
        }

        assertTrue(expectedExceptionCaught);
    }

    @Test
    public void addTag_addNonDuplicate_addsNormally() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertFalse(addressBook.containsTag(tagEconomist));
        addressBook.addTag(tagEconomist);
        assertTrue(addressBook.containsTag(tagEconomist));
    }

    @Test
    public void addTag_addDuplicate_throwsDuplicateTagException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean expectedExceptionCaught = false;

        try {
            addressBook.addTag(tagMathematician);
        } catch (DuplicateTagException e) {
            expectedExceptionCaught = true;
        }

        assertTrue(expectedExceptionCaught);
    }

    @Test
    public void containsPerson_personExists_returnsTrue() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean foundPerson = addressBook.containsPerson(personAliceBetsy);

        assertTrue(foundPerson);
    }

    @Test
    public void containsPerson_personNotExists_returnsFalse() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean foundPerson = addressBook.containsPerson(personCharlieDouglas);

        assertFalse(foundPerson);
    }

    @Test
    public void containsTag_tagExists_returnsTrue() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean foundTag = addressBook.containsTag(tagScientist);

        assertTrue(foundTag);
    }

    @Test
    public void containsTag_tagNotExists_returnsTrue() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean foundTag = addressBook.containsTag(tagEconomist);

        assertFalse(foundTag);
    }

    @Test
    public void removePerson_personExists_removesNormally() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertTrue(addressBook.containsPerson(personAliceBetsy));
        addressBook.removePerson(personAliceBetsy);
        assertFalse(addressBook.containsPerson(personAliceBetsy));
        assertTrue(addressBook.containsPerson(personBobChaplin));
    }

    @Test
    public void removePerson_personNotExists_throwsPersonNotFoundException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean expectedExceptionCaught = false;

        try {
            addressBook.removePerson(personCharlieDouglas);
        } catch (PersonNotFoundException e) {
            expectedExceptionCaught = true;
        }

        assertTrue(expectedExceptionCaught);
    }

    @Test
    public void removeTag_tagExistsAndUnused_removesNormally() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertTrue(addressBook.containsTag(tagScientist));
        addressBook.removeTag(tagScientist);
        assertFalse(addressBook.containsTag(tagScientist));

    }

    @Test
    public void removeTag_tagExistsAndUsed_throwsException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean expectedExceptionCaught = false;

        try {
            // This will violate the guarantee that every tag of every person is
            // found in the tag list.
            addressBook.removeTag(tagMathematician);
        } catch (Exception e) {
            expectedExceptionCaught = true;
        }

        assertTrue(expectedExceptionCaught);
    }

    @Test
    public void removeTag_tagNotExists_throwsTagNotFoundException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        boolean expectedExceptionCaught = false;

        try {
            addressBook.removeTag(tagEconomist);
        } catch (TagNotFoundException e) {
            expectedExceptionCaught = true;
        }

        assertTrue(expectedExceptionCaught);
    }

    @Test
    public void clear_emptiesTagListAndPersonList() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        addressBook.clear();

        // If the iterator does not have next element at start, then underlying
        // container has no elements.
        assertTrue(isEmpty(addressBook.getAllPersons()));
        assertTrue(isEmpty(addressBook.getAllTags()));
    }

    @Test
    public void getAllPersons_returnsAllPersons() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        UniquePersonList allPersons = addressBook.getAllPersons();
        UniquePersonList personsToCheck = new UniquePersonList(personAliceBetsy, personBobChaplin);

        assertTrue(isEqual(allPersons, personsToCheck));
    }

    @Test
    public void getAllTags_returnsAllTags() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        UniqueTagList allTags = addressBook.getAllTags();
        UniqueTagList tagsToCheck = new UniqueTagList(tagMathematician, tagScientist);

        assertTrue(isEqual(allTags, tagsToCheck));
    }
}
