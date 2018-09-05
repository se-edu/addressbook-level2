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
    public void isAnyNull() throws Exception {
        // empty list
        assertContainNotNull();

        // only one object
        assertContainNull((Object) null);
        assertContainNotNull(1);
        assertContainNotNull("");
        assertContainNotNull("abc");

        // all not null objects
        assertContainNotNull("abc", "ab", "a");
        assertContainNotNull(1, 2);

        // some null objects
        assertContainNotNull("abc", "abc");
        assertContainNotNull("abc", "", "abc", "ABC");
        assertContainNotNull("", "abc", "a", "abc");
        assertContainNotNull(1, Integer.valueOf(1));
        assertContainNull(null, 1, Integer.valueOf(1));
        assertContainNull(null, null);
        assertContainNull(null, "a", "b", null);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertContainNull(Object ... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertContainNotNull (Object ... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
