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
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.util.TestUtil;

public class SortCommandTest {
    @Test
    public void sortCommand_succeeds() {
        Person aaron = TestUtil.generateTestPerson("Aaron");
        Person john = TestUtil.generateTestPerson("John");
        Person zach = TestUtil.generateTestPerson("Zach");

        AddressBook book = TestUtil.createAddressBook(zach, john, aaron);

        SortCommand command = new SortCommand();

        command.setData(book, Collections.emptyList());
        command.execute();

        UniquePersonList people = book.getAllPersons();

        List<Person> SORTED_LIST = Arrays.asList(aaron, john, zach);

        try {
            UniquePersonList expected = new UniquePersonList(SORTED_LIST);
            assertTrue(people.equals(expected));
        } catch (Exception e) {
            System.out.println("Failed to create UniquePersonList.");
        }
    }
}