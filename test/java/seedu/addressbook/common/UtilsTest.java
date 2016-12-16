package seedu.addressbook.common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

public class UtilsTest {
    private static Set<Tag> tagSet;

    @BeforeClass
    public static void init() {
        tagSet = new HashSet<>();
        try {
            tagSet.add(new Tag("tag1"));
            tagSet.add(new Tag("tag2"));
            tagSet.add(new Tag("tag3"));
        } catch (IllegalValueException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void isAnyNull_singlePerson_shouldReturnNotFalse() {
        try {
            assertEquals("Passing in a person ", false,
                    Utils.isAnyNull(new Person(new Name("person"), new Phone("12345", true),
                            new Email("xuchen@u.nus.edu", false), new Address("311, Clementi Ave 2, #02-25", true),
                            new UniqueTagList(tagSet))));
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void equals_twoPersons_shouldReturnTrue() {
        try {
            assertEquals(
                    new Person(new Name("person"), new Phone("12345", true), new Email("xuchen@u.nus.edu", false),
                            new Address("311, Clementi Ave 2, #02-25", true), new UniqueTagList(tagSet)),
                    new Person(new Name("person"), new Phone("12345", true), new Email("xuchen@u.nus.edu", false),
                            new Address("311, Clementi Ave 2, #02-25", true), new UniqueTagList(tagSet)));
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void checkNull_shouldReturnNull() {
        assertEquals("Passing in Objects with null should return true", true,
                Utils.isAnyNull(2, new String("hi"), null));
    }

    @Test
    public void checkNull_shouldReturnNotNull() {
        assertEquals("Passing in Objects witout null should return false", false,
                Utils.isAnyNull(2, "Hillo", new Scanner(System.in)));
    }

    @Test
    public void checkUniqueElements_shouldReturnTrue() {
        ArrayList<Person> arrPerson = new ArrayList<>();

        arrPerson = new ArrayList<>();
        try {
            arrPerson.add(new Person(new Name("person"), new Phone("12345", true), new Email("xuchen@u.nus.edu", false),
                    new Address("311, Clementi Ave 2, #02-25", true), new UniqueTagList(tagSet)));

            arrPerson.add(
                    new Person(new Name("person"), new Phone("123451", true), new Email("xuchensad@u.nus.edu", true),
                            new Address("311, Clasdementi Ave 2, #02-25", true), new UniqueTagList(tagSet)));

            arrPerson.add(new Person(new Name("person"), new Phone("12345123", true),
                    new Email("xuchen@uasda.nus.edu", true), new Address("311, Clemeasdasnti Ave 2, #02-25", true),
                    new UniqueTagList(tagSet)));

            arrPerson.add(new Person(new Name("person"), new Phone("12345", true), new Email("xuchen@u.nus.edu", false),
                    new Address("31211, Clementi Ave 2, #02-25", true), new UniqueTagList(tagSet)));

        } catch (IllegalValueException e) {
            e.printStackTrace();
        }

        assertEquals("Passing in unique elements return true", true, Utils.elementsAreUnique(arrPerson));
    }

    @Test
    public void checkElementsAreUnique_IdenticalPersonsWithoutTag_shouldReturnFalse() {
        // It seems the method fails to detect identical Persons object because uniquetag list will always return a unique tag. 
        ArrayList<ReadOnlyPerson> persons = new ArrayList<>();

        try {
            persons.add(new Person(new Name("person"), new Phone("12345", true), new Email("xuchen@u.nus.edu", true),
                    new Address("311, Clementi Ave 2, #02-25", true)));
            persons.add(new Person(new Name("person"), new Phone("12345", true), new Email("xuchen@u.nus.edu", true),
                    new Address("311, Clementi Ave 2, #02-25", true)));
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("Passing in repeating elements return false", false, Utils.elementsAreUnique(persons));
    }
    
    @Test
    public void checkElementsAreUnique_identicalPersonsWithTags_shouldReturnFalse() {
        ArrayList<ReadOnlyPerson> persons = new ArrayList<>();

        try {
            persons.add(new Person(new Name("person"), new Phone("12345", true), new Email("xuchen@u.nus.edu", true),
                    new Address("311, Clementi Ave 2, #02-25", true),  new UniqueTagList(tagSet)));
            persons.add(new Person(new Name("person"), new Phone("12345", true), new Email("xuchen@u.nus.edu", true),
                    new Address("311, Clementi Ave 2, #02-25", true), new UniqueTagList(tagSet)));
            
        } catch (IllegalValueException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        assertEquals("Passing in repeating elements return false", false, Utils.elementsAreUnique(persons));
        
        
    }

}
