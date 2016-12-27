package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

/**
 * Test class for the Find Command
 */
public class FindCommandTest {

    private AddressBook addressBook;
    private TypicalPersons td;
    
    private final List<ReadOnlyPerson> EMPTY_LIST = Collections.emptyList();

    @Before 
    public void setUp() {
        this.addressBook = new TypicalPersons().getTypicalAddressBook();
        this.td = new TypicalPersons();
    }

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindCommandBehavior(new String[]{"Amy"}, TestUtil.createList(td.amy));
        
        //same word, different case: not matched
        assertFindCommandBehavior(new String[]{"aMy"}, EMPTY_LIST);
        
        //partial word: not matched
        assertFindCommandBehavior(new String[]{"my"}, EMPTY_LIST);
        
        //multiple words: matched
        assertFindCommandBehavior(new String[]{"Amy", "Bill", "Destiny"}, TestUtil.createList(td.amy, td.bill, td.candy));
    }


    /**
     * Executes the find command, and checks that the execution was what we had expected.
     */
    private void assertFindCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindCommand command = createFindCommand(keywords);
        CommandResult result = command.execute();
        
        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindCommand createFindCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindCommand command = new FindCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
