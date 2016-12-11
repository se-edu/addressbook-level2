package seedu.addressbook.data.tag;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import seedu.addressbook.data.tag.UniqueTagList.DuplicateTagException;
import seedu.addressbook.data.tag.UniqueTagList.TagNotFoundException;

public class UniqueTagListTest {
    private Tag tagA;
    private Tag tagB;
    private Tag tagC;
    private Tag tagD;
    
    private List<Tag> listWithDuplicateTags;
    private List<Tag> listWithoutDuplicateTags;
    private Set<Tag> setOfTags;
    
    
    private <T> int getNumElements(Iterable<T> iterable) {
        int numElements = 0;
        
        for (T elem : iterable) {
            numElements++;
        }
        
        return numElements;
    }
    
    private <T> boolean hasDuplicates(Iterable<T> iterable) {
        ArrayList<T> elementsEncounteredPreviously = new ArrayList<T>();
        
        for (T elem : iterable) {
            if (elementsEncounteredPreviously.contains(elem)) {
                return true; // has duplicates
            }
            elementsEncounteredPreviously.add(elem);
        }
        
        return false;
    }
    
    private <T> boolean isContentsIdentical(Iterable<T> iterableA, Iterable<T> iterableB) {
        ArrayList<T> listA = new ArrayList<T>();
        ArrayList<T> listB = new ArrayList<T>();
        
        for (T elemOfA : iterableA) {
            listA.add(elemOfA);
        }
        for (T elemOfB : iterableB) {
            listB.add(elemOfB);
        }
        
        assert !hasDuplicates(listA);
        assert !hasDuplicates(listB);
        
        // Check that listA is contained in listB.
        for (T elemOfA : listA) {
            if (!listB.contains(elemOfA)) {
                return false;
            }
        }
        // Check that listB is contained in listA.
        for (T elemOfB : listB) {
            if (!listA.contains(elemOfB)) {
                return false;
            }
        }
        
        // At this point, both lists contain each other i.e. they are identical.
        return true;
    }
    
    
    @Before
    public void setUp() throws Exception {
        tagA = new Tag("a");
        tagB = new Tag("b");
        tagC = new Tag("c");
        tagD = new Tag("d");
        
        listWithDuplicateTags = new ArrayList<Tag>();
        listWithDuplicateTags.add(tagA);
        listWithDuplicateTags.add(tagB);
        listWithDuplicateTags.add(tagB);
        listWithDuplicateTags.add(tagC);
        
        listWithoutDuplicateTags = new ArrayList<Tag>();
        listWithoutDuplicateTags.add(tagA);
        listWithoutDuplicateTags.add(tagB);
        listWithoutDuplicateTags.add(tagC);
        
        setOfTags = new HashSet<Tag>();
        setOfTags.add(tagA);
        setOfTags.add(tagB);
    }

    @Test
    public void create_noDuplicates_createsWithParameters() throws Exception {
        UniqueTagList tagList = new UniqueTagList(tagA, tagB, tagC);
        
        assertTrue(isContentsIdentical(tagList, listWithoutDuplicateTags));
    }
    
    @Test
    public void create_withDuplicates_throwsDuplicateTagException() {
        boolean expectedExceptionCaught = false;
        
        try {
            UniqueTagList tagList = new UniqueTagList(tagA, tagB, tagB, tagC);
        } catch (DuplicateTagException e) {
            expectedExceptionCaught = true;
        }
        
        assertTrue(expectedExceptionCaught);
    }
    
    @Test
    public void createFromCollection_noDuplicates_createsWithElementsInCollection() throws Exception {
        UniqueTagList tagList = new UniqueTagList(listWithoutDuplicateTags);
        
        assertTrue(isContentsIdentical(tagList, listWithoutDuplicateTags));
    }
    
    @Test
    public void createFromCollection_withDuplicates_throwsDuplicateTagException() {
        boolean expectedExceptionCaught = false;
        
        try {
            UniqueTagList tagListUnderTest = new UniqueTagList(listWithDuplicateTags);
        } catch (DuplicateTagException e) {
            expectedExceptionCaught = true;
        }
        
        assertTrue(expectedExceptionCaught);
    }
    
    @Test
    public void createFromSet_createsWithElementsInSet() {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        
        assertTrue(isContentsIdentical(tagList, setOfTags));
    }
    
    @Test
    public void createFromAnotherTagList_createsWithElementsInTagList() {
        UniqueTagList tagListToCreateFrom = new UniqueTagList(setOfTags);
        UniqueTagList tagList = new UniqueTagList(tagListToCreateFrom);
        
        assertTrue(isContentsIdentical(tagList, tagListToCreateFrom));
    }
    
    @Test
    public void toSet_returnsSetWithElementsInInternalList() {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        Set<Tag> setUnderTest = tagList.toSet();
        
        assertTrue(isContentsIdentical(tagList, setUnderTest));
    }
    
    @Test
    public void contains_elementInList_returnsTrue() {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        
        assertTrue(tagList.contains(tagA));
        assertTrue(tagList.contains(tagB));
    }
    
    @Test
    public void contains_elementNotInLIst_returnsFalse() {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        
        assertFalse(tagList.contains(tagD));
    }
    
    @Test
    public void add_elementNotInList_addsElement() throws Exception {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        
        tagList.add(tagC);
        assertTrue(tagList.contains(tagC));
    }
    
    @Test
    public void add_elementInLIst_throwsDuplicateElementException() {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        
        boolean expectedExceptionCaught = false;
        
        try {
            tagList.add(tagB);
        } catch (DuplicateTagException e) {
            expectedExceptionCaught = true;
        }
        
        assertTrue(expectedExceptionCaught);
    }
    
    @Test
    public void addAll_parameterListDisjoint_addsElementsInParameterList() throws Exception {
        UniqueTagList tagList = new UniqueTagList(tagC, tagD);
        UniqueTagList tagListToAdd = new UniqueTagList(tagA, tagB);
        UniqueTagList expectedTagList = new UniqueTagList(tagA, tagB, tagC, tagD);
        
        tagList.addAll(tagListToAdd);
        
        assertTrue(isContentsIdentical(tagList, expectedTagList));
    }
    
    @Test
    public void addAll_parameterLisNottDisjoint_throwsDuplicateElementException() throws Exception {
        UniqueTagList tagList = new UniqueTagList(tagC, tagD);
        UniqueTagList tagListToAdd = new UniqueTagList(tagA, tagB, tagC);
        
        boolean expectedExceptionCaught = false;
        
        try {
            tagList.addAll(tagListToAdd);
        } catch (DuplicateTagException e) {
            expectedExceptionCaught = true;
        }
        
        assertTrue(expectedExceptionCaught);
    }
    
    @Test
    public void mergeFrom_addsElementsInParameterListNotInOriginalList() throws Exception {
        UniqueTagList tagList = new UniqueTagList(tagC, tagD);
        UniqueTagList tagListToAdd = new UniqueTagList(tagA, tagB, tagC);
        UniqueTagList expectedTagList = new UniqueTagList(tagA, tagB, tagC, tagD);
        
        tagList.mergeFrom(tagListToAdd);
        
        assertTrue(isContentsIdentical(tagList, expectedTagList));
    }
    
    @Test
    public void remove_tagInList_removesTag() throws Exception {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        
        tagList.remove(tagB);
        
        assertTrue(tagList.contains(tagA));
        assertFalse(tagList.contains(tagB));
    }
    
    @Test
    public void remove_tagNotInList_throwsTagNotFoundException() {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        
        boolean expectedExceptionCaught = false;
        
        try {
            tagList.remove(tagC);
        } catch (TagNotFoundException e) {
            expectedExceptionCaught = true;
        }
        
        assertTrue(expectedExceptionCaught);
    }
    
    @Test
    public void clear_removesAllElements() {
        UniqueTagList tagList = new UniqueTagList(setOfTags);
        
        tagList.clear();
        
        assertTrue(getNumElements(tagList) == 0);
    }
    
    @Test
    public void setTags_setsTagListToNewList() throws Exception {
        UniqueTagList tagList = new UniqueTagList(tagA, tagB, tagC);
        UniqueTagList tagListReplacement = new UniqueTagList(tagD);
        
        tagList.setTags(tagListReplacement);
        
        assertTrue(isContentsIdentical(tagList, tagListReplacement));
    }

}
