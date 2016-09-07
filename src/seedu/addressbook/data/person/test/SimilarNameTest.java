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
    
    @Test
    public void isSimilar_differentNames_false() throws IllegalValueException {
        Name p1 = new Name("Love");
        Name p2 = new Name("Hurts");
        assertFalse(p1.isSimilar(p2));
    }
    
    @Test
    public void isSimilar_caseInsensitive_true() throws IllegalValueException {
        Name p1 = new Name("igot");
        Name p2 = new Name("IGOT");
        assertTrue(p1.isSimilar(p2));
    }
    
    @Test
    public void isSimilar_spaces_true() throws IllegalValueException {
        Name p1 = new Name("nolife");
        Name p2 = new Name("nolife     ");
        assertTrue(p1.isSimilar(p2));
    }



}
