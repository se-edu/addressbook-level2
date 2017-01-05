package seedu.addressbook.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
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
     */
    public static AddressBook createAddressBook(Person... persons) {
        AddressBook addressBook = new AddressBook();

        for (Person person : persons) {
            try {
                addressBook.addPerson(person);
            } catch (DuplicatePersonException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
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
     * Executes the command, and asserts the result message is as expected.
     */
    public static void assertCommandResult(Command command, AddressBook addressBook, AddressBook expectedAddressBook,
                                                                                               String expectedMessage) {
        CommandResult result = command.execute();

        // asserts the result message is correct as expected
        // TODO: overwrite equals method in CommandResult and replace with equals method below
        assertEquals(expectedMessage, result.feedbackToUser);

        // TODO: overwrite equals method in AddressBook and replace with equals method below
        assertEquals(addressBook.getAllPersons(), expectedAddressBook.getAllPersons());
    }
}
