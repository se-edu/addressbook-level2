package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TypicalPersons;

import static org.junit.Assert.assertEquals;

import java.util.*;

public class SortCommandTest {

    private TypicalPersons td = new TypicalPersons();
    private AddressBook typicalAddressBook = td.getTypicalAddressBook();
    private List<ReadOnlyPerson> listWithAllTypicalPersons = Arrays.asList(td.getTypicalPersons());

    @Test
    public void sortCommand_validData_correctlySorted() {
        typicalAddressBook.sort();
        listWithAllTypicalPersons.sort((a, b) -> a.getName().fullName.compareToIgnoreCase(b.getName().fullName));

        Iterator<Person> iter = typicalAddressBook.getAllPersons().iterator();
        for (ReadOnlyPerson person : listWithAllTypicalPersons) {
            assertEquals(person, iter.next());
        }
    }
}
