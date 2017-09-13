package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

import java.util.*;


import static org.junit.Assert.*;

public class FindAddressCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindAddressCommandBehavior(new String[]{"1"}, Arrays.asList(td.amy));

        //same word, different case: not matched
        assertFindAddressCommandBehavior(new String[]{"cLementi"}, Collections.emptyList());

        //partial word: not matched
        assertFindAddressCommandBehavior(new String[]{"lementi"}, Collections.emptyList());

        //multiple words: matched
        assertFindAddressCommandBehavior(new String[]{"Clementi", "NUS", "Candy", "Destiny"},
                Arrays.asList(td.amy, td.bill, td.candy, td.dan));

        //repeated keywords: matched
        assertFindAddressCommandBehavior(new String[]{"1", "1"}, Arrays.asList(td.amy));

        //Keyword matching a word in name: not matched
        assertFindAddressCommandBehavior(new String[]{"Amy"}, Collections.emptyList());
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindAddressCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindAddressCommand command = createFindAddressCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindAddressCommand createFindAddressCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindAddressCommand command = new FindAddressCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
