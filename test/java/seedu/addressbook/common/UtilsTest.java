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
    public void isAnyNull() throws Exception {
        //empty
        assertNoNull();

        //all object null
        assertSomeNull(null,null,null,null);

        //one or more object null
        assertSomeNull((Object)null);
        assertSomeNull("abc","ab",null,null);
        assertSomeNull("abc",null,null,null);
        assertSomeNull(1,"abc",'a',new Double(1.0),null);

        //all objects not null
        assertNoNull("abc");
        assertNoNull(1);
        assertNoNull(1,"abc",'a',new Double(1.0));

    }

    private void assertSomeNull(Object...objects) {
        assertTrue(Utils.isAnyNull(objects));
    }

    private void assertNoNull(Object...objects) {
        assertFalse(Utils.isAnyNull(objects));
    }
}
