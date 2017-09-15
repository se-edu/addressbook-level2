package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.util.TypicalPersons;

public class FindAllCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //same word, same case: matched
        assertFindAllCommandBehavior(new String[]{"Amy"}, Arrays.asList(td.amy));

        //same word, different case: matched
        assertFindAllCommandBehavior(new String[]{"aMy"}, Arrays.asList(td.amy));

        //partial word: matched
        assertFindAllCommandBehavior(new String[]{"my"}, Arrays.asList(td.amy));

        //multiple words: matched
        assertFindAllCommandBehavior(new String[]{"my", "Bil", "and"},
                Arrays.asList(td.amy, td.bill, td.candy));

        //repeated keywords: matched
        assertFindAllCommandBehavior(new String[]{"Amy", "Amy"}, Arrays.asList(td.amy));

        //Keyword matching a word in address: not matched
        assertFindAllCommandBehavior(new String[]{"Clementi"}, Collections.emptyList());
    }

    /**
     * Executes the find command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertFindAllCommandBehavior(String[] keywords, List<Person> expectedPersonList) {
        FindAllCommand command = createFindAllCommand(keywords);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private FindAllCommand createFindAllCommand(String[] keywords) {
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindAllCommand command = new FindAllCommand(keywordSet);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
