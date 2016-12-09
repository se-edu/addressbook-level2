package seedu.addressbook.commands;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.testutil.TestDataHelper;
import seedu.addressbook.testutil.TestMain;

import java.util.Collections;
import java.util.List;

/**
 * Parent class for all commands test
 */
public class CommandTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    protected TestMain testMain;
    protected TestDataHelper helper;

    @Before
    public void setUp() {
        this.testMain = new TestMain();
        this.helper = new TestDataHelper();
    }

    /**
     * Executes the command and confirms that the result message is correct.
     * Both the 'address book' and the 'last shown list' are expected to be
     * empty.
     * 
     * @see #assertCommandBehavior(String, String, AddressBook, List)
     */
    protected void assertCommandBehavior(String inputCommand, String expectedMessage) throws IllegalValueException {
        assertCommandBehavior(inputCommand, expectedMessage, new AddressBook(), Collections.emptyList());
    }

    /**
     * Executes the command and confirms that the result message is correct and
     * also confirms that the following three parts of the LogicManager object's
     * state are as expected:<br>
     * - the internal do do bird data are same as those in the
     * {@code expectedAddressBook} <br>
     * - the backing list shown by UI matches the {@code shownList} <br>
     * - {@code expectedAddressBook} was saved to the storage file. <br>
     */
    protected void assertCommandBehavior(String inputCommand, String expectedMessage, AddressBook expectedAddressBook,
            List<? extends ReadOnlyPerson> expectedShownList) throws IllegalValueException {
        // Execute the command
        CommandResult result = testMain.runCommand(inputCommand);
        assertEquals(expectedMessage, result.feedbackToUser);
        for (int i = 0; i < expectedShownList.size(); i++) {
            assertEquals(expectedShownList.get(i), testMain.getLastShownList().get(i));
        }

        // Confirm the state of data (saved and in-memory) is as expected
        assertEquals(expectedAddressBook, testMain.getAddressBook());

    }
}
