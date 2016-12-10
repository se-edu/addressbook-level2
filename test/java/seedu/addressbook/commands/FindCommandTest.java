package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalTestPersons;

/**
 * Test class for the Find Command
 */
public class FindCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private AddressBook addressBook;
    private AddressBook unmutatedAddressBook;

    @Before public void setUp() {
        this.addressBook = new TypicalTestPersons().getTypicalAddressBook();
        this.unmutatedAddressBook = new TypicalTestPersons().getTypicalAddressBook();
    }

    @Test
    public void execute_find_matchesOnlyIfCaseSensitive() throws IllegalValueException {
        List<ReadOnlyPerson> expectedOne = TestUtil.createList(new Person(TypicalTestPersons.amy));
        List<ReadOnlyPerson> expectedZero = Collections.emptyList();

        String[] caseSensitiveKeywords = {"Amy"};
        String[] caseInsensitiveKeywords = {"aMy"};
        
        FindCommand command = createFindCommand(caseSensitiveKeywords);
        assertCommandBehavior(command, Command.getMessageForPersonListShownSummary(expectedOne),
                unmutatedAddressBook, addressBook);
        
        command = createFindCommand(caseInsensitiveKeywords);
        assertCommandBehavior(command, Command.getMessageForPersonListShownSummary(expectedZero),
                unmutatedAddressBook, addressBook);


    }

    @Test
    public void execute_find_matchesOnlyFullWordsInNames() throws IllegalValueException {
        List<ReadOnlyPerson> expectedZero = Collections.emptyList();
        
        String[] keywords = {"my"};
        
        FindCommand command = createFindCommand(keywords);
        assertCommandBehavior(command, Command.getMessageForPersonListShownSummary(expectedZero),
                unmutatedAddressBook, addressBook);

    }

    @Test
    public void execute_find_multipleMatches() throws IllegalValueException {
        List<ReadOnlyPerson> expectedTwo = TestUtil.createList(new Person(TypicalTestPersons.amy),
                new Person(TypicalTestPersons.bill));
        
        String[] keywords = {"Amy", "Bill"};
        
        FindCommand command = createFindCommand(keywords);
        assertCommandBehavior(command, Command.getMessageForPersonListShownSummary(expectedTwo),
                unmutatedAddressBook, addressBook);

    }

    /**
     * Executes the find command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehavior(Command command, String expectedMessage, 
            AddressBook expectedAddressBook, AddressBook actualAddressBook) {

        CommandResult result = command.execute();
        
        assertEquals(expectedMessage, result.feedbackToUser);
        assertEquals(expectedAddressBook, actualAddressBook);
    }

    
    /**
     * Creates a new find command.
     * 
     * @param keywords to search for in the names' of persons
     */
    private FindCommand createFindCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindCommand command = new FindCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
