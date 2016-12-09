package seedu.addressbook.commands;

import java.util.Collections;
import java.util.List;

import org.junit.Test;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.testutil.TypicalTestPersons;

/**
 * Test class for the Find Command
 */
public class FindCommandTest extends CommandTest {

    @Test
    public void execute_find_isCaseSensitive() throws IllegalValueException {
        List<Person> expectedOne = helper.generatePersonList(new Person(TypicalTestPersons.claude));
        List<Person> expectedZero = Collections.emptyList();

        assertCommandBehavior("find Claude", Command.getMessageForPersonListShownSummary(expectedOne),
                testMain.getAddressBook(), expectedOne);

        assertCommandBehavior("find cLaude", Command.getMessageForPersonListShownSummary(expectedZero),
                testMain.getAddressBook(), expectedZero);
    }

    @Test
    public void execute_find_onlyMatchesFullWordsInNames() throws IllegalValueException {
        List<Person> expectedList = Collections.emptyList();

        assertCommandBehavior("find Laude", Command.getMessageForPersonListShownSummary(expectedList),
                testMain.getAddressBook(), expectedList);
    }

    @Test
    public void execute_find_multipleMatches() throws IllegalValueException {
        List<Person> expectedList = helper.generatePersonList(new Person(TypicalTestPersons.claude),
                new Person(TypicalTestPersons.hopper));

        assertCommandBehavior("find Claude Hopper", Command.getMessageForPersonListShownSummary(expectedList),
                testMain.getAddressBook(), expectedList);
    }

}
