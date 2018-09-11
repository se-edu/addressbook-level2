package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.commands.SortCommand;


import static org.junit.Assert.*;

public class SortCommandTest {

    @Test
    public void assertInvalidOrderString() {
        assertConstructingInvalidSortCmdThrowsException("name", "123asd");
        assertConstructingInvalidSortCmdThrowsException("name", " ");
    }

    /**
     * Asserts that attempting to construct a sort command with the supplied
     * invalid data throws an IllegalValueException
     */
    private void assertConstructingInvalidSortCmdThrowsException(String propertyName, String orderString) {
        try {
            new SortCommand(propertyName, orderString);
        } catch (NumberFormatException e) {
            return;
        }

        String error = String.format(
                "A sort command was successfully constructed with invalid input: %s %s ",
                propertyName, orderString);
        fail(error);
    }
}