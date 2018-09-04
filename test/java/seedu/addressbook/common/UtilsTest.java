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
        assertNotUnique(1, Integer.valueOf(1));
        assertNotUnique(null, 1, Integer.valueOf(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
    }
    @Test
    public void elementsAreNull() throws Exception {
        //empty list
        assertNotContainsNull();

        //only one object
        assertContainsNull((Object) null);
        assertNotContainsNull(1);
        assertNotContainsNull("hello");

        //multiple objects
        assertContainsNull((Object) null, 1, 2, 3);
        assertContainsNull("hello", 5, (Object) null);
        assertContainsNull(275, (Object) null, "hello");
        assertNotContainsNull("hello", 555, "bye bye");
        assertNotContainsNull("hello", 2);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertContainsNull(Object... objects) {assertTrue(Utils.isAnyNull(objects));}

    private void assertNotContainsNull(Object... objects) {assertFalse(Utils.isAnyNull(objects));}
}