package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

    @Test
    public boolean isAnyNull() throws Exception {
        // empty list
        assertNoNull();

        // no null
        assertNoNull("a", "b", "c");
        assertNoNull(1, 2, 3, 4);
        assertNoNull(1);

        // have null
        assertHaveNull(null);
        assertHaveNull("1", "2", null);
        assertHaveNull(null, null, null);

    }
    private void assertNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull(Arrays.asList(objects)));
    }

    private void assertHaveNull(Object... objects) {
        assertTrue(Utils.isAnyNull(Arrays.asList(objects)));
    }

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

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
