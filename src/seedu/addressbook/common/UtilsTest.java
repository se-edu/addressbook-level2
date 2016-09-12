package seedu.addressbook.common;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

public class UtilsTest {

    private int ARRAY_LENGTH = 5;
    private int ELEMENT_ONE_INDEX = 1;
    private int ELEMENT_ZERO_INDEX = 0;
    private int ELEMENT_TWO_INDEX = 2;
    
    //////////////////////////////////////////
    // tests for isAnyNull(Object... items) //
    //////////////////////////////////////////
    
    @Test
    public void noNullObjects_returnFalse() {
        Object[] testArr = makeArrFilledWithObjects();
        assertFalse(Utils.isAnyNull(testArr));
    }
    
    // all elements are null, return true
    @Test
    public void allNullObjects_returnTrue() {
        Object[] testArr = makeArrFilledWithNulls();
        assertTrue(Utils.isAnyNull(testArr));
    }
    
    // one element is null, rest all objects, returns true
    @Test
    public void oneNullObject_returnTrue() {
        Object[] testArr = makeArrFilledWithNulls();
        makeOneArrElementAnObject(testArr);
        assertTrue(Utils.isAnyNull(testArr));
    }
    
    // one element is not null, rest all null, returns true
    @Test
    public void oneNonNullObject_returnTrue() {
        Object[] testArr = makeArrFilledWithObjects();
        makeOneArrElementANull(testArr);
        assertTrue(Utils.isAnyNull(testArr));
    }
    
    //////////////////////////////////////////////////////
    // tests for elementsAreUnique(Collection<?> items) //
    //////////////////////////////////////////////////////
    
    @Test
    public void oneNullRestObjects_returnTrue() {
        ArrayList<Object> testArr = makeArrayListFilledWithObjects();
        makeOneArrayListElementANull(testArr);
        assertTrue(Utils.elementsAreUnique(testArr));
    }
    
    @Test
    public void allUniqueObjects_returnTrue() {
        ArrayList<Object> test = makeArrayListFilledWithObjects();
        assertTrue(Utils.elementsAreUnique(test));
    }
    
    @Test
    public void allNulls_returnFalse() {
        assertFalse(Utils.elementsAreUnique(makeArrayListFilledWithNulls()));
    }
    
    @Test
    public void allUniqueObjectsOneDuplicate_returnFalse() {
        ArrayList<Object> test = makeArrayListFilledWithObjects();
        duplicateZerothElementToFirst(test);
        assertFalse(Utils.elementsAreUnique(test));
    }
    
    @Test
    public void allUniqueObjectsTwoNulls_returnFalse() {
        ArrayList<Object> test = makeArrayListFilledWithObjects();
        test.set(ELEMENT_ZERO_INDEX, null);
        duplicateZerothElementToFirst(test);
        assertFalse(Utils.elementsAreUnique(test));
    }
    
    @Test 
    public void allDuplicates_returnFalse() {
        ArrayList<Object> test = new ArrayList<Object>();
        Object repeatedObject = new Object();
        for (int i=0; i<ARRAY_LENGTH; i++) {
            test.add(repeatedObject);
        }
        assertFalse(Utils.elementsAreUnique(test));
    }
    
    @Test
    public void twoUniqueObjectsThreeNulls_returnFalse() {
        ArrayList<Object> test = makeArrayListFilledWithNulls();
        test.set(ELEMENT_ZERO_INDEX, new Object());
        test.set(ELEMENT_ONE_INDEX, new Object());
        assertFalse(Utils.elementsAreUnique(test));
    }
    
    @Test
    public void twoDuplicateObjectsOneNull_returnFalse() {
        ArrayList<Object> test = makeArrayListFilledWithObjects();
        duplicateZerothElementToFirst(test);
        test.set(ELEMENT_TWO_INDEX, null);
        assertFalse(Utils.elementsAreUnique(test));
    }
    
    @Test
    public void twoDuplicateObjectsThreeNulls_returnFalse() {
        ArrayList<Object> test = makeArrayListFilledWithNulls();
        test.set(ELEMENT_ZERO_INDEX, new Object());
        duplicateZerothElementToFirst(test);
        assertFalse(Utils.elementsAreUnique(test));
    }
    
    
    ///////////////////////
    // Utility functions //
    ///////////////////////
    
    // creates an array filled with objects
    public Object[] makeArrFilledWithObjects() {
        Object[] objArr = new Object[ARRAY_LENGTH];
        for (int i=0; i<ARRAY_LENGTH; i++) {
            objArr[i] = new Object();
        }
        return objArr;
    }
    
    // creates an array filled with nulls
    public Object[] makeArrFilledWithNulls() {
        Object[] objArr = new Object[ARRAY_LENGTH];
        for (int i=0; i<ARRAY_LENGTH; i++) {
            objArr[i] = null;
        }
        return objArr;
    }
    
    // makes the 1th element an object
    public void makeOneArrElementAnObject(Object[] arr) {
        arr[ELEMENT_ONE_INDEX] = new Object();
    }
    
    // makes the 1th element a null
    public void makeOneArrElementANull(Object[] arr) {
        arr[ELEMENT_ONE_INDEX] = null;
    }

    public void makeOneArrayListElementANull(ArrayList<Object> arrList) {
        arrList.set(ELEMENT_ONE_INDEX, null);
    }

    public void makeOneArrayListElementAnObject(ArrayList<Object> arrList) {
        arrList.set(ELEMENT_ONE_INDEX, new Object());
    }
    
    // creates a linked list filled with objects
    public ArrayList<Object> makeArrayListFilledWithObjects() {
        ArrayList<Object>arrList = new ArrayList<Object>();
        for (int i=0; i<ARRAY_LENGTH; i++) {
            arrList.add(new Object());
        }
        return arrList;   
    }
    
    // creates a linked list filled with nulls
    public ArrayList<Object> makeArrayListFilledWithNulls() {
        ArrayList<Object>arrList = new ArrayList<Object>();
        for (int i=0; i<ARRAY_LENGTH; i++) {
            arrList.add(null);
        }
        return arrList;
    }
    
    // duplicate 0th item in linked list into 1st
    public void duplicateZerothElementToFirst(ArrayList<Object> arrList) {
        arrList.set(ELEMENT_ONE_INDEX, arrList.get(ELEMENT_ZERO_INDEX));
    }
}
