package seedu.addressbook.data.person;

import org.junit.*;

import seedu.addressbook.data.exception.IllegalValueException;

import static org.junit.Assert.*;

public class NameTest {

	@Test
	public void testIsSimilar_nullName() throws IllegalValueException{
		Name sample1 = new Name("test");
		Name sample2 = null;
		assertFalse(sample1.isSimilar(sample2));
	}
	
	@Test
	public void testIsSimilar_extraSpaces() throws IllegalValueException {
		Name sample1 = new Name ("test");
		Name sample2 = new Name ("t    e     s     t");
		assertFalse(sample1.isSimilar(sample2));
	}
	
	@Test
	public void testIsSimilar_differenetOrder() throws IllegalValueException {
		Name sample1 = new Name ("test this first");
		Name sample2 = new Name ("first test this");
		assertTrue(sample1.isSimilar(sample2));
	}
	
	@Test 
	public void testIsSimilar_sameNames() throws IllegalValueException {
		Name sample1 = new Name ("Test");
		Name sample2 = new Name ("Test");
		assertTrue(sample1.isSimilar(sample2));
	}
	
	@Test
	public void testIsSimilar_differentCases() throws IllegalValueException {
		Name sample1 = new Name ("Test");
		Name sample2 = new Name ("TeST");
		assertTrue(sample1.isSimilar(sample2));
	}
}
