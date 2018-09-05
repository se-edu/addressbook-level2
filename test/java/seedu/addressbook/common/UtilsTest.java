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

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    @Test
    public void ifAnyVoid() {
        Object nullObject = null;

        // empty list
        assertNotNull();

        // Single null Object
        assertNull(nullObject);

        // Single non-null data type
        assertNotNull('a', 'b');
        assertNotNull(Integer.valueOf(1), Integer.valueOf(2));
        assertNotNull(1, 2);
        assertNotNull("ABC", "abc");
        assertNotNull(true, false);

        // mixture of non-null data types
        assertNotNull('a', "ABC");
        assertNotNull(true, Integer.valueOf(2));
        assertNotNull("abc", Integer.valueOf(2));

        // mixture of non-null and null data types
        assertNull(nullObject, "AbCdE");
        assertNull(Integer.valueOf(10), nullObject);
        assertNull(nullObject, nullObject);
    }

    private void assertNull(Object... objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNotNull(Object... objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
