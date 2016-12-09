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

/**
 * we check, in particular, for the two guarantees specified by the AddressBook class
 *  - Every tag found in every person will also be found in the tag list.
 *  - The tags in each person point to tag objects in the master list. (== equality)
 */
public class AddressBookTest {
	
	private Tag tagDead;
	private Tag tagRepublican;
	private Tag tagDemocrat;
	private Tag tagIndependent;

	private Person personBarack; // has tagDemocrat
	private Person personGeorge; // has tagRepublican
	private Person personBill;   // has tagDemocrat
	private Person personMonroe; // has tagIndependent, tagDead
	
	/**
	 * Initialises an AddressBook with some default values.
	 * 
	 * Contains personBarack and personBill.
	 * Contains used tag tagDemocrat and unused tag tagRepublican.
	 * Does not contain tagIndependent.
	 * 
	 * @return the initialised AddressBook
	 * @throws Exception (formally, but this will NOT happen due to the parameters specifically chosen)
	 */
	private AddressBook createDefaultAddressBook() throws Exception {
		UniqueTagList tags = new UniqueTagList(tagDemocrat, tagRepublican);
		UniquePersonList persons = new UniquePersonList(personBarack, personBill);
		
		AddressBook addressBook = new AddressBook(persons, tags);
		
		return addressBook;
	}
	
	/**
	 * checks if the underlying container behind an iterable is empty
	 * 
	 * @param it, the iterable
	 * @return whether the container is empty
	 */
	private <T> boolean isEmpty(Iterable<T> it) {
		return !it.iterator().hasNext();
	}
	
	/**
	 * checks if the underlying containers behind two iterables are equal
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
		// Tag constructor will not throw as the strings supplied match the regex \p{Alnum}+
		tagRepublican = new Tag("republican");
		tagDemocrat = new Tag("democrat");
		tagIndependent = new Tag("independent");
		
		// UniqueTagList constructor will not throw as there are no duplicate elements
		personBarack = new Person(new Name("Barack"),
                                   new Phone("155426351", false),
                                   new Email("barack@whitehouse.gov", false),
                                   new Address("White House", false),
                                   new UniqueTagList(tagDemocrat));
		personGeorge = new Person(new Name("George"),
				                   new Phone("125469835", false),
                                   new Email("george@whitehouse.gov", false),
                                   new Address("White House", false),
                                   new UniqueTagList(tagRepublican));
		personBill   = new Person(new Name("Bill"),
                                   new Phone("154982351", false),
                                   new Email("bill@whitehouse.gov", false),
                                   new Address("White House", false),
                                   new UniqueTagList(tagDemocrat));
		personMonroe = new Person(new Name("Monroe"),
                                   new Phone("168463354", false),
                                   new Email("email@notinvented.yet", false),
                                   new Address("James Monroe Tomb, Richmond, Virginia", false),
                                   new UniqueTagList(tagIndependent, tagDead));
	}
	
	
	@Test
	public void sync_emptyInitialTagList_addsAllIntoTagList() throws Exception {
		UniqueTagList emptyTagList = new UniqueTagList();
		UniquePersonList persons = new UniquePersonList(personBill, personGeorge); // no duplicates
		
		AddressBook addressBook = new AddressBook(persons, emptyTagList);
		
		assertTrue(addressBook.containsTag(tagDemocrat));
		assertTrue(addressBook.containsTag(tagRepublican));
		assertFalse(addressBook.containsTag(tagIndependent));
		
	}
	
	@Test
	public void sync_addNewElementWhoseTagsNotInTagList_addsMissingTagsIntoTagList() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		assertFalse(addressBook.containsTag(tagIndependent));
		assertFalse(addressBook.containsTag(tagDead));
		addressBook.addPerson(personMonroe);
		assertTrue(addressBook.containsTag(tagIndependent));
		assertTrue(addressBook.containsTag(tagDead));
	}
	
	@Test
	public void sync_defaultAddressBook_tagEqualityPropertyHolds() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		UniquePersonList persons = addressBook.getAllPersons();
		UniqueTagList tags = addressBook.getAllTags();
		
		boolean allPersonTagsValid = true; // all person tags are equal to some tag in master list
		for (Person person : persons) {
			for (Tag personTag : person.getTags()) {
				boolean personTagValid = false; // particular person tag is equal to some tag in master list
				
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
		
		assertFalse(addressBook.containsPerson(personGeorge));
		addressBook.addPerson(personGeorge);
		assertTrue(addressBook.containsPerson(personGeorge));
	}
	
	@Test
	public void addPerson_addNonDuplicateWhoseTagNotInTagList_addsNormally() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		assertFalse(addressBook.containsPerson(personMonroe));
		addressBook.addPerson(personMonroe);
		assertTrue(addressBook.containsPerson(personMonroe));
	}
	
	@Test
	public void addPerson_addDuplicate_throwsDuplicatePersonException() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		boolean expectedExceptionCaught = false;
		
		try {
			addressBook.addPerson(personBarack);
		} catch (DuplicatePersonException e) {
			expectedExceptionCaught = true;
		}
		
		assertTrue(expectedExceptionCaught);
	}
	
	@Test
	public void addTag_addNonDuplicate_addsNormally() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		assertFalse(addressBook.containsTag(tagIndependent));
		addressBook.addTag(tagIndependent);
		assertTrue(addressBook.containsTag(tagIndependent));
	}
	
	@Test
	public void addTag_addDuplicate_throwsDuplicateTagException() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		boolean expectedExceptionCaught = false;
		
		try {
			addressBook.addTag(tagDemocrat);
		} catch (DuplicateTagException e) {
			expectedExceptionCaught = true;
		}
		
		assertTrue(expectedExceptionCaught);
	}
	
	@Test
	public void containsPerson_personExists_returnsTrue() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		boolean foundPerson = addressBook.containsPerson(personBarack);
		
		assertTrue(foundPerson);
	}
	
	@Test
	public void containsPerson_personNotExists_returnsFalse() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		boolean foundPerson = addressBook.containsPerson(personGeorge);
		
		assertFalse(foundPerson);
	}
	
	@Test
	public void containsTag_tagExists_returnsTrue() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		boolean foundTag = addressBook.containsTag(tagRepublican);
		
		assertTrue(foundTag);
	}
	
	@Test
	public void containsTag_tagNotExists_returnsTrue() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		boolean foundTag = addressBook.containsTag(tagIndependent);
		
		assertFalse(foundTag);
	}
	
	@Test
	public void removePerson_personExists_removesNormally() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		assertTrue(addressBook.containsPerson(personBarack));
		addressBook.removePerson(personBarack);
		assertFalse(addressBook.containsPerson(personBarack));
		assertTrue(addressBook.containsPerson(personBill));
	}
	
	@Test
	public void removePerson_personNotExists_throwsPersonNotFoundException() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		boolean expectedExceptionCaught = false;
		
		try {
			addressBook.removePerson(personGeorge);
		} catch (PersonNotFoundException e) {
			expectedExceptionCaught = true;
		}
		
		assertTrue(expectedExceptionCaught);
	}
	
	@Test
	public void removeTag_tagExistsAndUnused_removesNormally() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		assertTrue(addressBook.containsTag(tagRepublican));
		addressBook.removeTag(tagRepublican);
		assertFalse(addressBook.containsTag(tagRepublican));
		
	}
	
	@Test
	public void removeTag_tagExistsAndUsed_throwsException() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		boolean expectedExceptionCaught = false;
		
		try {
			// this will violate the guarantee that every tag of every person is found in the tag list
			addressBook.removeTag(tagDemocrat);
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
			addressBook.removeTag(tagIndependent);
		} catch (TagNotFoundException e) {
			expectedExceptionCaught = true;
		}
		
		assertTrue(expectedExceptionCaught);
	}
	
	@Test
	public void clear_emptiesTagListAndPersonList() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		addressBook.clear();
		
		// iterator does not have next element at start=> underlying container has no elements
		assertTrue(isEmpty(addressBook.getAllPersons()));
		assertTrue(isEmpty(addressBook.getAllTags()));
	}
	
	@Test
	public void getAllPersons_returnsAllPersons() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		UniquePersonList allPersons = addressBook.getAllPersons();
		UniquePersonList personsToCheck = new UniquePersonList(personBarack, personBill);
		
		assertTrue(isEqual(allPersons, personsToCheck));
	}
	
	@Test
	public void getAllTags_returnsAllTags() throws Exception {
		AddressBook addressBook = createDefaultAddressBook();
		
		UniqueTagList allTags = addressBook.getAllTags();
		UniqueTagList tagsToCheck = new UniqueTagList(tagDemocrat, tagRepublican);
		
		assertTrue(isEqual(allTags, tagsToCheck));
	}
}
