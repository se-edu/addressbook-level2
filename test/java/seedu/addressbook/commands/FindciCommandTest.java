package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class FindciCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindciCommandBehavior(new String[]{"Amy"}, Arrays.asList(td.amy));

        //same word, different case: matched
        assertFindciCommandBehavior(new String[]{"aMy"}, Arrays.asList(td.amy));

        //partial word: not matched
        assertFindciCommandBehavior(new String[]{"my"}, Collections.emptyList());

        //multiple words: matched
        assertFindciCommandBehavior(new String[]{"Amy", "Bill", "Candy", "Destiny"},
                Arrays.asList(td.amy, td.bill, td.candy));

        //repeated keywords: matched
        assertFindciCommandBehavior(new String[]{"Amy", "Amy"}, Arrays.asList(td.amy));

        //Keyword matching a word in address: not matched
        assertFindciCommandBehavior(new String[]{"Clementi"}, Collections.emptyList());
    }

    /**
     * Executes the findci command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindciCommandBehavior(String[] keywords, List<ReadOnlyPerson> expectedPersonList) {
        FindciCommand command = createFindciCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindciCommand createFindciCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindciCommand command = new FindciCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
