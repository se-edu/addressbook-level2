package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class FindtagCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindtagCommandBehavior("test", Arrays.asList(td.dan));

        //same word, different case: not matched
        assertFindtagCommandBehavior("Test", Collections.emptyList());

        //partial word: not matched
        assertFindtagCommandBehavior("est", Collections.emptyList());

        //Keyword matching a word in address: not matched
        assertFindtagCommandBehavior("Clementi", Collections.emptyList());
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindtagCommandBehavior(String keyword, List<ReadOnlyPerson> expectedPersonList) {
        FindtagCommand command = createFindtagCommand(keyword);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindtagCommand createFindtagCommand(String keyword) {
        FindtagCommand command = new FindtagCommand(keyword);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
