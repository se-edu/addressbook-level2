package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class SortCommandTest {

    private TypicalPersons td = new TypicalPersons();
    private AddressBook typicalAddressBook = td.getTypicalAddressBook();
    private List<ReadOnlyPerson> unsortedList = Arrays.asList(td.getTypicalPersons());
    private List<ReadOnlyPerson> sortedList = Arrays.asList(td.getSortedPersons());
    private SortCommand sortCommand = new SortCommand();

    @Test
    public void sortAlphabeticalOrder() {
        sortCommand.setData(typicalAddressBook, unsortedList);
        CommandResult result = sortCommand.execute();
        Optional<List<? extends ReadOnlyPerson>> expectedList = result.getRelevantPersons();
        assertTrue(expectedList.isPresent());
        assertSorted(expectedList.get(), this.sortedList);
    }


    /**
     * Compares expectedList and sortedList to check if expectedList is sorted correctly.
     */
    private void assertSorted(List<? extends ReadOnlyPerson> expectedList, List<? extends ReadOnlyPerson> sortedList) {
        assertEquals(expectedList.size(), sortedList.size());
        for (int i = 0; i < expectedList.size(); i++) {
            assertTrue(expectedList.get(i).isSamePerson(sortedList.get(i)));
        }
    }
}
