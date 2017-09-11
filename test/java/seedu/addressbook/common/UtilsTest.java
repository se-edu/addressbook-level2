package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {


    @Test
    public void elementsAreUnique() throws Exception {
        // empty list
        assertAreUnique();

        // only one object
        assertAreUnique((Object) null);
        assertAreUnique(1);
        assertAreUnique("");
        assertAreUnique("abc");

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertAreUnique(1, 2);

        // some identical objects
        assertNotUnique("abc", "abc");
        assertNotUnique("abc", "", "abc", "ABC");
        assertNotUnique("", "abc", "a", "abc");
        assertNotUnique(1, new Integer(1));
        assertNotUnique(null, 1, new Integer(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
    }

    @Test
    public void elementsAreNull() throws Exception {

        // not null objects

        assertsNotNull(1);
        assertsNotNull("");
        assertsNotNull("abc");
        assertsNotNull("abc", "abc");
        assertsNotNull("abc", "", "abc", "ABC");
        assertsNotNull("", "abc", "a", "abc");
        assertsNotNull(1, new Integer(1));

        // some null objects

        assertsAreNull(null, 1, new Integer(1));
        assertsAreNull((Object) null);
        assertsAreNull(null, null);
        assertsAreNull(null, "a", "b", null);
    }
    public void assertsAreNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }
    public void assertsNotNull(Object... objects)  {
       assertFalse(Utils.isAnyNull(objects));
    }
    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
