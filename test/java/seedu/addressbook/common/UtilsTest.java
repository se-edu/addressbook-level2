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

    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    @Test
        public void isAnyNull(){

        //no objects
        assertNoNull();

        //only has null objects
        assertHasNull((Object) null);

        //no null objects
        assertNoNull("hello", 1, 2);
        assertNoNull("1", 1, 3);
        assertNoNull(1, "abc");

        //mixture of objects + one null object
        assertHasNull(1,2,null);
        assertHasNull("abc", "bca","hi", null);
        assertHasNull(null, 1,"hey");

    }
    private void assertHasNull(Object... objects) {
        assertTrue(Utils.isAnyNull((objects)));
    }

    private void assertNoNull(Object... objects) {
        assertFalse(Utils.isAnyNull((objects)));

    }
}
