package seedu.addressbook.data.person.test;

import static org.junit.Assert.*;
import org.junit.Test;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Name;

public class SimilarNameTest {
    
    @Test
    public void isSimilar_sameName_true() throws IllegalValueException {
        Name n1 = new Name("ineedsleep");
        Name n2 = new Name("ineedsleep");
        assertTrue(n1.isSimilar(n2));
    }


}
