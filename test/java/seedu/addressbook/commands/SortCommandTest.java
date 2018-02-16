package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.util.TypicalPersons;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class SortCommandTest {

    private final AddressBook addressBook = new AddressBook();
    private final TypicalPersons td = new TypicalPersons();
    private List<ReadOnlyPerson> listWithSomeTypicalPersonsSorted = Arrays.asList(td.amy, td.candy, td.dan);

    @Test
    public void execute_sort() throws UniquePersonList.DuplicatePersonException {
        assertSortCommandBehavior();
    }

    /**
     * Executes the sort command on an unsorted list of persons
     * @throws UniquePersonList.DuplicatePersonException
     */
    private void assertSortCommandBehavior() throws UniquePersonList.DuplicatePersonException{
        SortCommand command = createSortCommand();
        command.addressBook.addPerson(td.dan);
        command.addressBook.addPerson(td.amy);
        command.addressBook.addPerson(td.candy);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(listWithSomeTypicalPersonsSorted), result.feedbackToUser);

    }

    /**
     * Helper function for creating a sort command to be used for testing
     * @return A sort command with an empty addressBook
     */
    private SortCommand createSortCommand() {
        SortCommand command = new SortCommand();
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
