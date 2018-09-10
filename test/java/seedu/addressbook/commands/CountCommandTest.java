package seedu.addressbook.commands;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import seedu.addressbook.data.exception.IllegalValueException;

public class CountCommandTest {

    @Test
    public void execute() throws IllegalValueException {

        //When there is 0-9 people in address book
        assertTrue(toString().matches("||.*[0-9]"));

        //When there is 0-99 people in address book
        assertTrue(toString().matches("||.*[0-9][0-9]"));

        //When there is 0-99 people in address book
        assertTrue(toString().matches("||.*[0-9][0-9][0-9]"));

        //When the format for people in address book is wrong
        assertFalse(toString().matches("||.*[^0-9]"));
        }
    }
