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

    @Before public void setUp() {
        this.addressBook = new TypicalPersons().getTypicalAddressBook();
    }

    @Test
    public void execute_sameWordSameCase_matched() throws IllegalValueException {
        List<ReadOnlyPerson> expected = TestUtil.createList(new Person(TypicalPersons.amy));
        
        String[] keywordsWithMatchingCase = {"Amy"};
        
        FindCommand command = createFindCommand(keywordsWithMatchingCase);
        assertCommandBehavior(command, expected, addressBook);
    }
    
    @Test
    public void execute_sameWordDifferentCase_notMatched() throws IllegalValueException {
        String[] keywordsWithNonMatchingCase = {"aMy"};
        
        FindCommand command = createFindCommand(keywordsWithNonMatchingCase);
        assertCommandBehavior(command, EMPTY_LIST, addressBook);
    }
    
    @Test
    public void execute_partialKeyword_notMatched() throws IllegalValueException {        
        String[] keywords = {"my"};
        
        FindCommand command = createFindCommand(keywords);
        assertCommandBehavior(command, EMPTY_LIST, addressBook);
    }
    
    @Test
    public void execute_multipleKeywords_matched() throws IllegalValueException {
        List<ReadOnlyPerson> expected = TestUtil.createList(new Person(TypicalPersons.amy),
                                                            new Person(TypicalPersons.bill),
                                                            new Person(TypicalPersons.candy));
        
        String[] keywords = {"Amy", "Bill", "Destiny"};
        
        FindCommand command = createFindCommand(keywords);
        assertCommandBehavior(command, expected, addressBook);
    }

    /**
     * Executes the find command, and checks that the execution was what we had expected.
     */
    private void assertCommandBehavior(Command command, List<ReadOnlyPerson> expectedPersonList, AddressBook actualAddressBook) {
        
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
