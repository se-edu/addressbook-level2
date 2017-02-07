package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class UtilsTest {
    @Test
    public void isAnyNull() {
        // empty list
        assertFalse(Utils.isAnyNull());

        // Any non-empty list
        assertFalse(Utils.isAnyNull(new Object(), new Object()));
        assertFalse(Utils.isAnyNull("test"));
        assertFalse(Utils.isAnyNull(""));

        // non empty list with just one null at the beginning
        assertTrue(Utils.isAnyNull((Object) null));
        assertTrue(Utils.isAnyNull(null, "", new Object()));
        assertTrue(Utils.isAnyNull(null, new Object(), new Object()));

        // non empty list with nulls in the middle
        assertTrue(Utils.isAnyNull(new Object(), null, null, "test"));
        assertTrue(Utils.isAnyNull("", null, new Object()));

        // non empty list with one null as the last element
        assertTrue(Utils.isAnyNull("", new Object(), null));
        assertTrue(Utils.isAnyNull(new Object(), new Object(), null));

        // confirms nulls inside the list are not considered
        List<Object> nullList = Arrays.asList((Object) null);
        assertFalse(Utils.isAnyNull(nullList));

        // 2d array tests
        ArrayList<ArrayList<String>> nullArrayList = new ArrayList<ArrayList<String>>(3);
        for(int i = 0; i < 3; i++) {
        	nullArrayList.add( i, new ArrayList<String>());
        }
        assertFalse(Utils.isAnyNull(nullArrayList));
        
        ArrayList<ArrayList<String>> notNullArrayList = new ArrayList<ArrayList<String>>(3);
        for(int i = 0; i < 3; i++) {
        	notNullArrayList.add( i, new ArrayList<String>());
        	notNullArrayList.get(i).add("notNull");
        }
        assertFalse(Utils.isAnyNull(notNullArrayList));
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
        assertNotUnique(1, new Integer(1));
        assertNotUnique(null, 1, new Integer(1));
        assertNotUnique(null, null);
        assertNotUnique(null, "a", "b", null);
        
        //2d ArrayList tests
        ArrayList<ArrayList<String>> nullArrayList = new ArrayList<ArrayList<String>>(3);
        ArrayList<ArrayList<String>> nullArrayList2 = new ArrayList<ArrayList<String>>(3);
        for(int i = 0; i < 3; i++) {
        	nullArrayList.add( i, new ArrayList<String>());
        	nullArrayList2.add( i, new ArrayList<String>());
        }
        assertNotUnique(nullArrayList, nullArrayList2);
        
        nullArrayList.get(1).add("notNull");        
        assertAreUnique(nullArrayList, nullArrayList2);
        
        nullArrayList2.get(1).add("notNull");        
        assertNotUnique(nullArrayList, nullArrayList2);
        
        nullArrayList2.get(2).add("notNull");        
        assertAreUnique(nullArrayList, nullArrayList2);
    }
    
    private void assertAreUnique(Object... objects) {
        assertTrue(Utils.elementsAreUnique(Arrays.asList(objects)));
    }

    private void assertNotUnique(Object... objects) {
        assertFalse(Utils.elementsAreUnique(Arrays.asList(objects)));
    }
}
