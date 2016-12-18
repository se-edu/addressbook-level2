package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {
    private static final Object NULL_OBJECT = null;

    @Test
    public void isAnyNull() {
        List<Object> emptyList = createList();
        List<Object> nonEmptyList = createList("A string");

        // empty list
        assertFalse(Utils.isAnyNull());

        // Any non-empty list
        assertFalse(Utils.isAnyNull(new Object(), new Object()));
        assertFalse(Utils.isAnyNull(emptyList));

        // non empty list with just one at the beginning
 //       assertTrue(Utils.isAnyNull(null));
        assertTrue(Utils.isAnyNull(NULL_OBJECT, "", new Object(), null));
        assertTrue(Utils.isAnyNull(NULL_OBJECT, emptyList, nonEmptyList));

        // non empty list with nulls in the middle
        assertTrue(Utils.isAnyNull(nonEmptyList, NULL_OBJECT, NULL_OBJECT, emptyList));
        assertTrue(Utils.isAnyNull("", NULL_OBJECT, new Object()));

        // non empty list with one null as the last element
        assertTrue(Utils.isAnyNull("", new Object(), NULL_OBJECT));
        assertTrue(Utils.isAnyNull(nonEmptyList, emptyList, NULL_OBJECT));
    }

    @Test
    public void elementsAreUnique() throws Exception {
        // empty list
        assertAreUnique();

        // only one object
        assertAreUnique(NULL_OBJECT);
        assertAreUnique(1);

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertAreUnique(1, 2);

        // some identical objects
        assertNotUnique("abc", "", "abc", "ABC");
        assertNotUnique(NULL_OBJECT, 1, new Integer(1));
        assertNotUnique("", "abc", "a", "abc");
        assertNotUnique(NULL_OBJECT, "a", "b", NULL_OBJECT);
    }

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(createList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(createList(objects)));
    }
    
    private List<Object> createList(Object... objects) {
        return Arrays.asList(objects);
    }
}
