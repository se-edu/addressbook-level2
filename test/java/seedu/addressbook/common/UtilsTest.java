package seedu.addressbook.common;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

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

    @Test
    public void isAnyNull() {
        // no items
        assertFalse(Utils.isAnyNull());

        // varargs array that is null (i.e. no array)
        assertFalse(Utils.isAnyNull((Object[]) null));

        // one item which is null (i.e. an array with one null item)
        assertTrue(Utils.isAnyNull((Object) null));

        // at least one item, none of which are null
        assertFalse(Utils.isAnyNull("A"));
        assertFalse(Utils.isAnyNull("A", ""));
        assertFalse(Utils.isAnyNull(1, 2, "C"));

        // at least one item, some of which are null
        assertTrue(Utils.isAnyNull(null, 1));
        assertTrue(Utils.isAnyNull("A", null, "C"));
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
