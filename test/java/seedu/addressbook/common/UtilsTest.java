package seedu.addressbook.common;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.tag.Tag;

public class UtilsTest {
    private static Object nullObject = null;
    private static Tag tag1;
    private static Tag tag1Copy;
    private static Tag tag2;

    @Before
    public void setup() {
        tag1 = buildTag("tag1");
        tag1Copy = buildTag("tag1");
        tag2 = buildTag("tag2");
    }
    
    private static Tag buildTag(String tagName) {
        try {
            return new Tag(tagName);
        } catch (IllegalValueException e) {
            throw new RuntimeException("test tag name should be valid", e);
        }
    }

    @Test
    public void isAnyNull_emptyInput_returnsFalse() {
        assertFalse(Utils.isAnyNull());
    }

    @Test
    public void isAnyNull_allNulls_returnsTrue() {
        assertTrue(Utils.isAnyNull(nullObject));
        assertTrue(Utils.isAnyNull(nullObject, nullObject));
        assertTrue(Utils.isAnyNull(nullObject, nullObject, nullObject));
    }
    
    @Test
    public void isAnyNull_someNulls_returnsTrue() {
        assertTrue(Utils.isAnyNull(nullObject, ""));
        assertTrue(Utils.isAnyNull("", nullObject, ""));
        assertTrue(Utils.isAnyNull(new Object(), nullObject));
        
        ArrayList<String> emptyList = new ArrayList<String>();
        ArrayList<String> nonEmptyList = new ArrayList<String>();
        nonEmptyList.add("A string");

        assertTrue(Utils.isAnyNull(emptyList, nonEmptyList, nullObject));
        assertTrue(Utils.isAnyNull(nonEmptyList, nonEmptyList, nullObject, nullObject));
    }

    @Test
    public void isAnyNull_noNulls_returnsFalse() {
        assertFalse(Utils.isAnyNull(new Object()));
        assertFalse(Utils.isAnyNull(new Object(), new Object()));
        
        assertFalse(Utils.isAnyNull(""));
        
        ArrayList<String> emptyList = new ArrayList<String>();
        assertFalse(Utils.isAnyNull(emptyList));
        
        ArrayList<String> nonEmptyList = new ArrayList<String>();
        nonEmptyList.add("A string");

        assertFalse(Utils.isAnyNull(nonEmptyList, emptyList));
        assertFalse(Utils.isAnyNull(emptyList, "A string"));
    }

    @Test
    public void elementsAreUnique_emptyCollection_returnsTrue() {
        assertTrue(Utils.elementsAreUnique(new ArrayList<>()));
    }

    @Test
    public void elementsAreUnique_singleElement_returnsTrue() {
        assertTrue(Utils.elementsAreUnique(new ArrayList<Object>() {
            {
                add(nullObject);
            }
        }));
        assertTrue(Utils.elementsAreUnique(new ArrayList<String>() {
            {
                add("String1");
            }
        }));
        assertTrue(Utils.elementsAreUnique(new ArrayList<Tag>() {
            {
                add(tag1);
            }
        }));
    }

    @Test
    public void elementsAreUnique_sameElements_returnsFalse() {
        assertFalse(Utils.elementsAreUnique(new ArrayList<String>() {
            {
                add("abc");
                add("abc");
            }
        }));
        assertFalse(Utils.elementsAreUnique(new ArrayList<Tag>() {
            {
                add(tag1);
                add(tag1);
                add(tag1);
            }
        }));
    }

    @Test
    public void elementsAreUnique_allNulls_returnsFalse() {
        assertFalse(Utils.elementsAreUnique(new ArrayList<Object>() {
            {
                add(nullObject);
                add(nullObject);
            }
        }));
    }

    @Test
    public void elementsAreUnique_someIdenticalTags_returnsFalse() {
        // To compare tags only
        assertFalse(Utils.elementsAreUnique(new ArrayList<Tag>() {
            {
                add(tag1);
                add(tag2);
                add(tag2);
            }
        }));

        assertFalse(Utils.elementsAreUnique(new ArrayList<Tag>() {
            {
                add(tag1);
                add(tag1Copy);
                add(tag2);
            }
        }));
    }

    @Test
    public void elementsAreUnique_differentTags_returnsTrue() {
        assertTrue(Utils.elementsAreUnique(new ArrayList<Tag>() {
            {
                add(tag1);
                add(tag2);
            }
        }));

        assertTrue(Utils.elementsAreUnique(new ArrayList<Tag>() {
            {
                add(tag1);
                add(tag2);
                add(null);
            }
        }));
    }

}
