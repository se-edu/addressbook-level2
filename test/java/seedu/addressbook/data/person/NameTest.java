package seedu.addressbook.data.person;

import org.junit.Test;
import seedu.addressbook.data.exception.IllegalValueException;

import static org.junit.Assert.*;

public class NameTest {


    @Test
    public void isSimilar() throws IllegalValueException {
        Name john = new Name("john");
        Name john2 = new Name("john");
        Name JOHN = new Name("JOHN");
        Name johnathan = new Name("johnanthan");
        Name tim = new Name("tim");
        Name nullName = null;

        // one name is a subset of the other
        assertSimilarName(john, johnathan);

        // one name is not a subset of the other
        assertNotSimilarName(john, tim);

        // case-insensitive
        assertSimilarName(john, JOHN);

        // two identical names
        assertSimilarName(john, john);
        assertSimilarName(john, john2);

        // comparison with Name object referencing to null
        assertNotSimilarName(john, nullName);
    }

    private void assertSimilarName(Name name, Name otherName) {
        assertTrue(name.isSimilar(otherName));
    }

    private void assertNotSimilarName(Name name, Name otherName) {
        assertFalse(name.isSimilar(otherName));
    }
}