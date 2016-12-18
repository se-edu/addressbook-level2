package seedu.addressbook.data;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.addressbook.data.exception.ConstraintViolationException;
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

import static seedu.addressbook.util.TestUtil.isEmpty;
import static seedu.addressbook.util.TestUtil.isIdentical;
import static seedu.addressbook.util.TestUtil.getAllTags;
import static seedu.addressbook.util.TestUtil.getSize;

public class AddressBookTest {
    private Tag tagPrizeWinner;
    private Tag tagScientist;
    private Tag tagMathematician;
    private Tag tagEconomist;

    private Person aliceBetsy;
    private Person bobChaplin;
    private Person charlieDouglas;
    private Person davidElliot;
    
    private AddressBook defaultAddressBook;
    private AddressBook emptyAddressBook;

    /**
     * Initialises an AddressBook with some default values.
     * 
     * Contains aliceBetsy and bobChaplin.
     * Contains used tag tagMathematician and unused tag tagScientist.
     * Does not contain tagEconomist.
     */
    private AddressBook createDefaultAddressBook() {
        UniqueTagList tags;
        UniquePersonList persons;
        AddressBook addressBook;
        
        try {
            tags = new UniqueTagList(tagMathematician, tagScientist);
            persons = new UniquePersonList(aliceBetsy, bobChaplin);
            addressBook = new AddressBook(persons, tags);    
        } catch (DuplicateTagException | DuplicatePersonException e) {
            // The choice of Tag and Person values are such that this method never throws.
            // We should thus never reach this point.
            throw new RuntimeException();
        }

        return addressBook;
    }
    
    @Before
    public void setUp() throws Exception {
        tagPrizeWinner   = new Tag("prizewinner");
        tagScientist     = new Tag("scientist");
        tagMathematician = new Tag("mathematician");
        tagEconomist     = new Tag("economist");

        aliceBetsy     = new Person(new Name("Alice Betsy"),
                                    new Phone("91235468", false),
                                    new Email("alice@nushackers.org", false),
                                    new Address("8 Computing Drive, Singapore", false),
                                    new UniqueTagList(tagMathematician));

        bobChaplin     = new Person(new Name("Bob Chaplin"),
                                    new Phone("94321500", false),
                                    new Email("bob@nusgreyhats.org", false),
                                    new Address("9 Computing Drive", false),
                                    new UniqueTagList(tagMathematician));

        charlieDouglas = new Person(new Name("Charlie Douglas"),
                                    new Phone("98751365", false),
                                    new Email("charlie@nusgdg.org", false),
                                    new Address("10 Science Drive", false),
                                    new UniqueTagList(tagScientist));

        davidElliot    = new Person(new Name("David Elliot"),
                                    new Phone("84512575", false),
                                    new Email("douglas@nuscomputing.com", false),
                                    new Address("11 Arts Link", false),
                                    new UniqueTagList(tagEconomist, tagPrizeWinner));
        
        defaultAddressBook = createDefaultAddressBook();
        emptyAddressBook = new AddressBook();
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void syncTagsWithMasterList_emptyInitialTagList_addsAllIntoTagList() throws Exception {
        UniqueTagList emptyTagList = new UniqueTagList();
        UniquePersonList persons = new UniquePersonList(bobChaplin, charlieDouglas);
        AddressBook addressBookUnderTest = new AddressBook(persons, emptyTagList);
        
        UniqueTagList expectedTagList = new UniqueTagList(tagMathematician, tagScientist);
        
        assertTrue(isIdentical(expectedTagList, addressBookUnderTest.getAllTags()));

    }

    @Test
    public void syncTagsWithMasterList_someTagsNotInTagList_addsMissingTagsIntoTagList() throws Exception {
        assertFalse(defaultAddressBook.containsTag(tagEconomist));
        assertFalse(defaultAddressBook.containsTag(tagPrizeWinner));
        defaultAddressBook.addPerson(davidElliot);
        assertTrue(defaultAddressBook.containsTag(tagEconomist));
        assertTrue(defaultAddressBook.containsTag(tagPrizeWinner));
    }

    @Test
    public void syncTagsWithMasterList_defaultAddressBook_tagEqualityPropertyHolds() throws Exception {
        UniquePersonList persons = defaultAddressBook.getAllPersons();
        UniqueTagList tagsInPersons = getAllTags(persons);
        UniqueTagList tags = defaultAddressBook.getAllTags();
        
        // all person tags are equal to some tag in the master list
        boolean allPersonTagsValid = true;
        for (Tag personTag : tagsInPersons) {
            // particular person tag is equal to some tag in the master list
            boolean personTagValid = false;
            for (Tag tag : tags) {
                personTagValid = personTagValid || (personTag == tag);
            }
            allPersonTagsValid = allPersonTagsValid && personTagValid;
        }
        
        assertTrue(allPersonTagsValid);
    }

    @Test
    public void addPerson_personNotInList_addsNormally() throws Exception {
        // add person whose tag is in tag list
        assertFalse(defaultAddressBook.containsPerson(charlieDouglas));
        defaultAddressBook.addPerson(charlieDouglas);
        assertTrue(defaultAddressBook.containsPerson(charlieDouglas));
        
        // add person whose tag is not in tag list
        assertFalse(defaultAddressBook.containsPerson(davidElliot));
        defaultAddressBook.addPerson(davidElliot);
        assertTrue(defaultAddressBook.containsPerson(davidElliot));
    }

    @Test
    public void addPerson_personAlreadyInList_throwsDuplicatePersonException() throws Exception {
        thrown.expect(DuplicatePersonException.class);
        defaultAddressBook.addPerson(aliceBetsy);
    }
    
    @Test
    public void addPerson_personAlreadyInListButHasTagNotInList_tagNotAdded() throws Exception {
        aliceBetsy.setTags(new UniqueTagList(tagMathematician, tagPrizeWinner));
        
        try {
            defaultAddressBook.addPerson(aliceBetsy);
        } catch (DuplicatePersonException e) {
            assertFalse(defaultAddressBook.containsTag(tagPrizeWinner));
            return;
        }
        
        fail("Expected DuplicatePersonException was not caught.");
    }

    @Test
    public void addTag_tagNotInList_addsNormally() throws Exception {
        assertFalse(defaultAddressBook.containsTag(tagEconomist));
        defaultAddressBook.addTag(tagEconomist);
        assertTrue(defaultAddressBook.containsTag(tagEconomist));
    }

    @Test
    public void addTag_tagAlreadyInList_throwsDuplicateTagException() throws Exception {
        thrown.expect(DuplicateTagException.class);
        defaultAddressBook.addTag(tagMathematician);
    }

    @Test
    public void containsPerson() throws Exception {
        UniquePersonList personsWhoShouldBeIn = new UniquePersonList(aliceBetsy, bobChaplin);
        UniquePersonList personsWhoShouldNotBeIn = new UniquePersonList(charlieDouglas, davidElliot);
        
        for (Person personWhoShouldBeIn : personsWhoShouldBeIn) {
            assertTrue(defaultAddressBook.containsPerson(personWhoShouldBeIn));
        }
        for (Person personWhoShouldNotBeIn : personsWhoShouldNotBeIn) {
            assertFalse(defaultAddressBook.containsPerson(personWhoShouldNotBeIn));
        }
        
        UniquePersonList allPersons = new UniquePersonList(aliceBetsy, bobChaplin, charlieDouglas, davidElliot);
        
        for (Person person : allPersons) {
            assertFalse(emptyAddressBook.containsPerson(person));
        }
    }

    @Test
    public void containsTag() throws Exception {
        assertTrue(defaultAddressBook.containsTag(tagScientist));
        assertFalse(defaultAddressBook.containsTag(tagEconomist));
        
        UniqueTagList allTags = new UniqueTagList(tagPrizeWinner, tagScientist, tagMathematician, tagEconomist);
        
        for (Tag tag : allTags) {
            assertFalse(emptyAddressBook.containsTag(tag));
        }
    }

    @Test
    public void removePerson_personExists_removesNormally() throws Exception {
        int numberOfPersonsBeforeRemoval = getSize(defaultAddressBook.getAllPersons());
        defaultAddressBook.removePerson(aliceBetsy);
        int numberOfPersonsAfterRemoval = getSize(defaultAddressBook.getAllPersons());
        
        assertFalse(defaultAddressBook.containsPerson(aliceBetsy));
        assertTrue(numberOfPersonsBeforeRemoval == numberOfPersonsAfterRemoval + 1);
        
    }

    @Test
    public void removePerson_personNotExists_throwsPersonNotFoundException() throws Exception {
        thrown.expect(PersonNotFoundException.class);
        defaultAddressBook.removePerson(charlieDouglas);
    }

    @Test
    public void removeTag_tagExistsAndUnused_removesNormally() throws Exception {
        int numberOfTagsBeforeRemoval = getSize(defaultAddressBook.getAllTags());
        defaultAddressBook.removeTag(tagScientist);
        int numberOfTagsAfterRemoval = getSize(defaultAddressBook.getAllTags());
        
        assertFalse(defaultAddressBook.containsTag(tagScientist));
        assertTrue(numberOfTagsBeforeRemoval == numberOfTagsAfterRemoval + 1);

    }

    @Test
    public void removeTag_tagExistsAndUsed_throwsConstraintViolationException() throws Exception {
        thrown.expect(ConstraintViolationException.class);
        defaultAddressBook.removeTag(tagMathematician);
    }

    @Test
    public void removeTag_tagNotExists_throwsTagNotFoundException() throws Exception {
        thrown.expect(TagNotFoundException.class);
        defaultAddressBook.removeTag(tagEconomist);
    }

    @Test
    public void clear_emptiesTagListAndPersonList() throws Exception {
        defaultAddressBook.clear();

        // If the iterator does not have next element at start, then underlying
        // container has no elements.
        assertTrue(isEmpty(defaultAddressBook.getAllPersons()));
        assertTrue(isEmpty(defaultAddressBook.getAllTags()));
    }

    @Test
    public void getAllPersons_returnsAllPersons() throws Exception {
        UniquePersonList allPersons = defaultAddressBook.getAllPersons();
        UniquePersonList personsToCheck = new UniquePersonList(aliceBetsy, bobChaplin);

        assertTrue(isIdentical(allPersons, personsToCheck));
    }

    @Test
    public void getAllTags_returnsAllTags() throws Exception {
        UniqueTagList allTags = defaultAddressBook.getAllTags();
        UniqueTagList tagsToCheck = new UniqueTagList(tagMathematician, tagScientist);

        assertTrue(isIdentical(allTags, tagsToCheck));
    }
}
