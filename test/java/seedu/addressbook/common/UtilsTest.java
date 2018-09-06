package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import jdk.jshell.execution.Util;
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
        assertNotUnique(1, Integer.valueOf(1));
        assertNotUnique(null, 1, Integer.valueOf(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    @Test
    public void isAnyNull() {
        // all objects null
        assertAreNull(null, null);
        assertAreNull(null, null, null);

        // some objects null
        assertHasNull(1, null);
        assertHasNull("abc", null);
        assertHasNull(true, null);
        assertHasNull(1, null, 2);

        // all objects non-null
        assertAreNotNull(1, 2);
        assertAreNotNull("abc", "ABC");
        assertAreNotNull(1, 2, "abc", "ABC");
        assertAreNotNull(1, "abc", 2, "ABC");
        assertAreNotNull(true, false);
        assertAreNotNull(1, true, 2, false);
        assertAreNotNull("abc", true, "ABC", false);
        assertAreNotNull(1, true, "abc", 2, false, "ABC");
    }

    private void assertAreNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertHasNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertAreNotNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
