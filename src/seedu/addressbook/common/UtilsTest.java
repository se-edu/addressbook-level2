package seedu.addressbook.common;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.person.Person;

public class UtilsTest {
    
    private Utils utilsTest;
    
    @Before 
    public void setup() {
        utilsTest = new Utils();
    }
    
    @Test
    public void nullCheckerForNullItems() {
        boolean expected = true;
        boolean result = utilsTest.isAnyNull(10, null);
        assertEquals(expected, result);
    }
    
    @Test
    public void nullCheckerForNonNullItems() {
        boolean expected = false;
        int[] notNull = new int[10];
        for (int i = 0; i < 10; i++) {
            notNull[i] = 1;
        }
        boolean result = utilsTest.isAnyNull(notNull);
        assertEquals(expected, result);
    }
    
    @Test
    public void nonUniqueElements() {
        boolean expected = false;
        ArrayList<Integer> notUnique = new ArrayList<Integer>();
        for (int i = 0; i < 10; i++) {
            notUnique.add(0);
        }
        boolean result = utilsTest.elementsAreUnique(notUnique);
        assertEquals(expected, result);
    }
    
    @Test
    public void UniqueElements() {
        boolean expected = true;
        ArrayList<Integer> Unique = new ArrayList<Integer>();
        for (int i = 0; i < 10; i ++) {
            Unique.add(i);
        }
        boolean result = utilsTest.elementsAreUnique(Unique);
        assertEquals(expected, result);
    }
}
