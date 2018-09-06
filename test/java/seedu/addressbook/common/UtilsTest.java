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
        assertFalse(Utils.isAnyNull());

        // only one object
        Object object = new Object();
        object = null;
        assertTrue(Utils.isAnyNull(object));
        assertFalse(Utils.isAnyNull("1"));
        assertFalse(Utils.isAnyNull(""));

        //more than one object
        assertFalse(Utils.isAnyNull( "abc", "123"));
        assertTrue(Utils.isAnyNull( "abc", "123", object));

        //some identical object
        assertFalse(Utils.isAnyNull( "abc", "abc"));
        assertTrue(Utils.isAnyNull( object, object));
    }




    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
