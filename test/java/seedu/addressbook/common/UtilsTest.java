package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

import seedu.addressbook.data.tag.Tag;

public class UtilsTest {
    private static final Object NULL_OBJECT = null;

    @Test
    public void isAnyNull() {
        ArrayList<String> emptyList = new ArrayList<String>();
        ArrayList<String> nonEmptyList = new ArrayList<String>();
        nonEmptyList.add("A string");

        // empty list
        assertFalse(Utils.isAnyNull());

        // Any non-empty list
        assertFalse(Utils.isAnyNull(new Object(), new Object()));
        assertFalse(Utils.isAnyNull(emptyList));

        // non empty list with just one at the beginning
        assertTrue(Utils.isAnyNull(NULL_OBJECT));
        assertTrue(Utils.isAnyNull(NULL_OBJECT, "", new Object()));
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
        Tag tag1 = new Tag("tag1"),
            tag1Copy = new Tag("tag1"),
            tag2 = new Tag("tag2");

        // empty list
        assertAreUnique();

        // only one object
        assertAreUnique(NULL_OBJECT);
        assertAreUnique(tag1);

        // all objects unique
        assertAreUnique("abc", "ab", "a");
        assertAreUnique(tag1, tag2);

        // some identical objects
        assertNotUnique("abc", "", "abc", "ABC");
        assertNotUnique(NULL_OBJECT, tag1, tag1Copy, tag2);
        assertNotUnique("", "abc", "a", "abc");
        assertNotUnique(NULL_OBJECT, "a", "b", NULL_OBJECT);
    }

    private void assertAreUnique(Object... objects) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(objects));
        assertTrue(Utils.elementsAreUnique(list));
    }

    private void assertNotUnique(Object... objects) {
        ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(objects));
        assertFalse(Utils.elementsAreUnique(list));
    }
}
