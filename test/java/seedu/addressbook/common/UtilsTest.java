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
    public void isAnyNullTest() throws Exception{
        //check empty
        assertNotNull();

        //check 1 null
        assertAnyNull((Object) null);
        assertAnyNull("test1",null);
        assertAnyNull("test1","test2",null);
        assertAnyNull("test1","test2",1,null);

        //check few null
        assertAnyNull("test1",null,null);
        assertAnyNull("test1",1234,null,null);

        //check all null
        assertAnyNull(null,null);
        assertAnyNull(null,null,null);

        //check no null
        assertNotNull("test");
        assertNotNull("test1","test2");
        assertNotNull("test",1,2);
    }

    private void assertAnyNull(Object... objects){
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNotNull(Object... objects){
        assertFalse(Utils.isAnyNull(objects));
    }
}
