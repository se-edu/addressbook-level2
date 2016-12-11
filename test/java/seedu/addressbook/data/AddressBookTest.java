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

public class AddressBookTest {
    private Tag tagPrizeWinner;
    private Tag tagScientist;
    private Tag tagMathematician;
    private Tag tagEconomist;

    private Person aliceBetsy; // has tagMathematician
    private Person bobChaplin; // has tagMathematician
    private Person charlieDouglas; // has tagScientist
    private Person davidElliot; // has tagEconomist, tagPrizeWinner

    /**
     * Initialises an AddressBook with some default values.
     * 
     * Contains personAliceBetsy and personBobChaplin.
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
    }
    
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void syncTagsWithMasterList_emptyInitialTagList_addsAllIntoTagList() throws Exception {
        UniqueTagList emptyTagList = new UniqueTagList();
        UniquePersonList persons = new UniquePersonList(bobChaplin, charlieDouglas);

        AddressBook addressBook = new AddressBook(persons, emptyTagList);

        assertTrue(addressBook.containsTag(tagMathematician));
        assertTrue(addressBook.containsTag(tagScientist));
        assertFalse(addressBook.containsTag(tagEconomist));

    }

    @Test
    public void syncTagsWithMasterList_someTagsNotInTagList_addsMissingTagsIntoTagList() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertFalse(addressBook.containsTag(tagEconomist));
        assertFalse(addressBook.containsTag(tagPrizeWinner));
        addressBook.addPerson(davidElliot);
        assertTrue(addressBook.containsTag(tagEconomist));
        assertTrue(addressBook.containsTag(tagPrizeWinner));
    }

    @Test
    public void syncTagsWithMasterList_defaultAddressBook_tagEqualityPropertyHolds() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();
        UniquePersonList persons = addressBook.getAllPersons();
        UniqueTagList tagsInPersons = getAllTags(persons);
        UniqueTagList tags = addressBook.getAllTags();
        
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
        AddressBook addressBook = createDefaultAddressBook();
        
        // add person whose tag is in tag list
        assertFalse(addressBook.containsPerson(charlieDouglas));
        addressBook.addPerson(charlieDouglas);
        assertTrue(addressBook.containsPerson(charlieDouglas));
        
        // add person whose tag is not in tag list
        assertFalse(addressBook.containsPerson(davidElliot));
        addressBook.addPerson(davidElliot);
        assertTrue(addressBook.containsPerson(davidElliot));
    }

    @Test
    public void addPerson_personAlreadyInList_throwsDuplicatePersonException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();
        
        thrown.expect(DuplicatePersonException.class);
        addressBook.addPerson(aliceBetsy);
    }

    @Test
    public void addTag_tagNotInList_addsNormally() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertFalse(addressBook.containsTag(tagEconomist));
        addressBook.addTag(tagEconomist);
        assertTrue(addressBook.containsTag(tagEconomist));
    }

    @Test
    public void addTag_tagAlreadyInList_throwsDuplicateTagException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();
        
        thrown.expect(DuplicateTagException.class);
        addressBook.addTag(tagMathematician);
    }

    @Test
    public void containsPerson() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertTrue(addressBook.containsPerson(aliceBetsy));
        assertFalse(addressBook.containsPerson(charlieDouglas));
    }

    @Test
    public void containsTag() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();
        
        assertTrue(addressBook.containsTag(tagScientist));
        assertFalse(addressBook.containsTag(tagEconomist));
    }

    @Test
    public void removePerson_personExists_removesNormally() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertTrue(addressBook.containsPerson(aliceBetsy));
        addressBook.removePerson(aliceBetsy);
        assertFalse(addressBook.containsPerson(aliceBetsy));
        assertTrue(addressBook.containsPerson(bobChaplin));
    }

    @Test
    public void removePerson_personNotExists_throwsPersonNotFoundException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();
        
        thrown.expect(PersonNotFoundException.class);
        addressBook.removePerson(charlieDouglas);
    }

    @Test
    public void removeTag_tagExistsAndUnused_removesNormally() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        assertTrue(addressBook.containsTag(tagScientist));
        addressBook.removeTag(tagScientist);
        assertFalse(addressBook.containsTag(tagScientist));

    }

    @Test
    public void removeTag_tagExistsAndUsed_throwsConstraintViolationException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();
        
        thrown.expect(ConstraintViolationException.class);
        addressBook.removeTag(tagMathematician);
    }

    @Test
    public void removeTag_tagNotExists_throwsTagNotFoundException() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();
        
        thrown.expect(TagNotFoundException.class);
        addressBook.removeTag(tagEconomist);
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
        UniquePersonList personsToCheck = new UniquePersonList(aliceBetsy, bobChaplin);

        assertTrue(isIdentical(allPersons, personsToCheck));
    }

    @Test
    public void getAllTags_returnsAllTags() throws Exception {
        AddressBook addressBook = createDefaultAddressBook();

        UniqueTagList allTags = addressBook.getAllTags();
        UniqueTagList tagsToCheck = new UniqueTagList(tagMathematician, tagScientist);

        assertTrue(isIdentical(allTags, tagsToCheck));
    }
}
