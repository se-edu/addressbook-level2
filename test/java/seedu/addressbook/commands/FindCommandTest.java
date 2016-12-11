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
import seedu.addressbook.util.TypicalPersons;

/**
 * Test class for the Find Command
 */
public class FindCommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private AddressBook addressBook;
    private AddressBook unmutatedAddressBook;

    @Before public void setUp() {
        this.addressBook = new TypicalPersons().getTypicalAddressBook();
        this.unmutatedAddressBook = new TypicalPersons().getTypicalAddressBook();
    }

    @Test
    public void execute_sameWordSameCase_matched() throws IllegalValueException {
        List<ReadOnlyPerson> expected = TestUtil.createList(new Person(TypicalPersons.amy));
        
        String[] keywordsWithMatchingCase = {"Amy"};
        
        FindCommand command = createFindCommand(keywordsWithMatchingCase);
        assertCommandBehavior(command, Command.getMessageForPersonListShownSummary(expected),
                unmutatedAddressBook, addressBook);
    }
    
    @Test
    public void execute_sameWordDifferentCase_notMatched() throws IllegalValueException {
        List<ReadOnlyPerson> expected = Collections.emptyList();
        
        String[] keywordsWithNonMatchingCase = {"aMy"};
        
        FindCommand command = createFindCommand(keywordsWithNonMatchingCase);
        assertCommandBehavior(command, Command.getMessageForPersonListShownSummary(expected),
                unmutatedAddressBook, addressBook);
    }
    
    

    @Test
    public void execute_find_matchesOnlyFullWordsInNames() throws IllegalValueException {
        List<ReadOnlyPerson> expected = Collections.emptyList();
        
        String[] keywords = {"my"};
        
        FindCommand command = createFindCommand(keywords);
        assertCommandBehavior(command, Command.getMessageForPersonListShownSummary(expected),
                unmutatedAddressBook, addressBook);
    }

    @Test
    public void execute_find_multipleMatches() throws IllegalValueException {
        List<ReadOnlyPerson> expected = TestUtil.createList(new Person(TypicalPersons.amy),
                new Person(TypicalPersons.bill));
        
        String[] keywords = {"Amy", "Bill"};
        
        FindCommand command = createFindCommand(keywords);
        assertCommandBehavior(command, Command.getMessageForPersonListShownSummary(expected),
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
