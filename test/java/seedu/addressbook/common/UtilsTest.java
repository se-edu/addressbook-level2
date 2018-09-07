package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {

    @Test
    public void testIsNonNull() throws Exception {
        // empty list
        assertNoneNull();

        // one element
        assertNoneNull("asd");
        assertNoneNull(2);
        assertSomeNull((Object)null);

        // two elements
        assertNoneNull("asd", 2);
        assertNoneNull(2, 2);
        assertSomeNull("asd", null);
        assertSomeNull(null, null);
        assertSomeNull(null, 2);

        // more elements
        assertNoneNull("asd", 2, new BigInteger("4"));
        assertNoneNull(1, 2, 3, 4);
        assertNoneNull("one", "two", "three", "four", "five");
        assertSomeNull("asd", 2, null);
        assertSomeNull(null, 2, null, "asd");
        assertSomeNull(2, "asd", null, "qwe", null);
    }

    private void assertSomeNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNoneNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
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
}
