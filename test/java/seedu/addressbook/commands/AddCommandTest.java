package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.util.TestUtil;

public class AddCommandTest {
    private static final List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();
    private static final Set<String> EMPTY_STRING_SET = Collections.emptySet();

    @Test
    public void addCommand_invalidName_throwsException() {
        final String[] invalidNames = { "", " ", "[]\\[;]" };
        for (String name : invalidNames) {
            assertConstructingInvalidAddCmdThrowsException(name, Phone.EXAMPLE, true, Email.EXAMPLE, false,
                    Address.EXAMPLE, true, EMPTY_STRING_SET);
        }
    }

    @Test
    public void addCommand_invalidPhone_throwsException() {
        final String[] invalidNumbers = { "", " ", "1234-5678", "[]\\[;]", "abc", "a123", "+651234" };
        for (String number : invalidNumbers) {
            assertConstructingInvalidAddCmdThrowsException(Name.EXAMPLE, number, false, Email.EXAMPLE, true,
                    Address.EXAMPLE, false, EMPTY_STRING_SET);
        }
    }

    @Test
    public void addCommand_invalidEmail_throwsException() {
        final String[] invalidEmails = { "", " ", "def.com", "@", "@def", "@def.com", "abc@",
                                         "@invalid@email", "invalid@email!", "!invalid@email" };
        for (String email : invalidEmails) {
            assertConstructingInvalidAddCmdThrowsException(Name.EXAMPLE, Phone.EXAMPLE, false, email, false,
                    Address.EXAMPLE, false, EMPTY_STRING_SET);
        }
    }

    @Test
    public void addCommand_invalidAddress_throwsException() {
        final String[] invalidAddresses = { "", " " };
        for (String address : invalidAddresses) {
            assertConstructingInvalidAddCmdThrowsException(Name.EXAMPLE, Phone.EXAMPLE, true, Email.EXAMPLE,
                    true, address, true, EMPTY_STRING_SET);
        }
    }

    @Test
    public void addCommand_invalidTags_throwsException() {
        final String[][] invalidTags = { { "" }, { " " }, { "'" }, { "[]\\[;]" }, { "validTag", "" },
                                         { "", " " } };
        for (String[] tags : invalidTags) {
            Set<String> tagsToAdd = new HashSet<>(Arrays.asList(tags));
            assertConstructingInvalidAddCmdThrowsException(Name.EXAMPLE, Phone.EXAMPLE, true, Email.EXAMPLE,
                    true, Address.EXAMPLE, false, tagsToAdd);
        }
    }

    /**
     * Asserts that attempting to construct an add command with the supplied
     * invalid data throws an IllegalValueException
     */
    private void assertConstructingInvalidAddCmdThrowsException(String name, String phone,
            boolean isPhonePrivate, String email, boolean isEmailPrivate, String address,
            boolean isAddressPrivate, Set<String> tags) {
        try {
            new AddCommand(name, phone, isPhonePrivate, email, isEmailPrivate, address, isAddressPrivate,
                    tags);
        } catch (IllegalValueException e) {
            return;
        }
        String error = String.format(
                "An add command was successfully constructed with invalid input: %s %s %s %s %s %s %s %s",
                name, phone, isPhonePrivate, email, isEmailPrivate, address, isAddressPrivate, tags);
        fail(error);
    }

    @Test
    public void addCommand_validData_correctlyConstructed() throws Exception {
        AddCommand command = new AddCommand(Name.EXAMPLE, Phone.EXAMPLE, true, Email.EXAMPLE, false,
                Address.EXAMPLE, true, EMPTY_STRING_SET);
        ReadOnlyPerson p = command.getPerson();

        // TODO: add comparison of tags to person.equals and equality methods to
        // individual fields that compare privacy to simplify this
        assertEquals(Name.EXAMPLE, p.getName().fullName);
        assertEquals(Phone.EXAMPLE, p.getPhone().value);
        assertTrue(p.getPhone().isPrivate());
        assertEquals(Email.EXAMPLE, p.getEmail().value);
        assertFalse(p.getEmail().isPrivate());
        assertEquals(Address.EXAMPLE, p.getAddress().value);
        assertTrue(p.getAddress().isPrivate());
        boolean isTagListEmpty = !p.getTags().iterator().hasNext();
        assertTrue(isTagListEmpty);
    }

    @Test
    public void addCommand_emptyAddressBook_addressBookContainsPerson() {
        Person p = TestUtil.generateTestPerson();
        AddCommand command = new AddCommand(p);
        AddressBook book = new AddressBook();
        command.setData(book, EMPTY_PERSON_LIST);
        CommandResult result = command.execute();
        UniquePersonList people = book.getAllPersons();

        assertTrue(people.contains(p));
        assertEquals(1, people.immutableListView().size());
        assertFalse(result.getRelevantPersons().isPresent());
        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, p), result.feedbackToUser);
    }

    @Test
    public void addCommand_addressBookAlreadyContainsPerson_addressBookUnmodified() throws Exception {
        Person p = TestUtil.generateTestPerson();
        AddressBook book = new AddressBook();
        book.addPerson(p);
        AddCommand command = new AddCommand(p);
        command.setData(book, EMPTY_PERSON_LIST);
        CommandResult result = command.execute();

        assertFalse(result.getRelevantPersons().isPresent());
        assertEquals(AddCommand.MESSAGE_DUPLICATE_PERSON, result.feedbackToUser);
        UniquePersonList people = book.getAllPersons();
        assertTrue(people.contains(p));
        assertEquals(1, people.immutableListView().size());
    }
}
