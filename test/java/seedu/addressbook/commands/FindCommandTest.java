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
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TestUtil;
import seedu.addressbook.util.TypicalPersons;

/**
 * Test class for the Find Command
 */
public class FindCommandTest {

    private AddressBook addressBook;
    
    private final List<ReadOnlyPerson> EMPTY_LIST = Collections.emptyList();

    @Before 
    public void setUp() {
        this.addressBook = new TypicalPersons().getTypicalAddressBook();
    }

    @Test
    public void execute_sameWordSameCase_matched() throws IllegalValueException {
        List<ReadOnlyPerson> expected = TestUtil.createList(new Person(TypicalPersons.amy()));
        String[] keywordsWithMatchingCase = {"Amy"};
        
        assertFindCommandBehavior(keywordsWithMatchingCase, expected, addressBook);
    }
    
    @Test
    public void execute_sameWordDifferentCase_notMatched() throws IllegalValueException {
        String[] keywordsWithNonMatchingCase = {"aMy"};  
        assertFindCommandBehavior(keywordsWithNonMatchingCase, EMPTY_LIST, addressBook);
    }
    
    @Test
    public void execute_partialKeyword_notMatched() throws IllegalValueException {        
        String[] keywords = {"my"};
        assertFindCommandBehavior(keywords, EMPTY_LIST, addressBook);
    }
    
    @Test
    public void execute_multipleKeywords_matched() throws IllegalValueException {
        List<ReadOnlyPerson> expected = TestUtil.createList(new Person(TypicalPersons.amy()),
                                                            new Person(TypicalPersons.bill()),
                                                            new Person(TypicalPersons.candy()));
        
        String[] keywords = {"Amy", "Bill", "Destiny"};
        assertFindCommandBehavior(keywords, expected, addressBook);
    }

    /**
     * Executes the find command, and checks that the execution was what we had expected.
     */
    private void assertFindCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList, AddressBook actualAddressBook) {
        FindCommand command = createFindCommand(keywords);
        CommandResult result = command.execute();
        
        AddressBook expectedAddressBook = new TypicalPersons().getTypicalAddressBook();
        
        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
        assertEquals(expectedAddressBook, actualAddressBook);
    }

    private FindCommand createFindCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindCommand command = new FindCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }
}
