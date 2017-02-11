package seedu.addressbook.data.tag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import seedu.addressbook.common.Utils;
import seedu.addressbook.data.exception.DuplicateDataException;


/**
 * A list of tags. Does not allow nulls or duplicates.
 *
 * @see Tag#equals(Object)
 * @see Utils#elementsAreUnique(Collection)
 */
public class UniqueTagList implements Iterable<Tag> {

    /**
     * Signals that an operation would have violated the 'no duplicates' property of the list.
     */
    public static class DuplicateTagException extends DuplicateDataException {
        protected DuplicateTagException() {
            super("Operation would result in duplicate tags");
        }
    }

    private final List<Tag> internalList = new ArrayList<>();

    /**
     * Constructs an empty TagList.
     */
    public UniqueTagList() {}

    /**
     * Constructs a tag list with the given tags.
     */
    public UniqueTagList(Tag... tags) throws DuplicateTagException {
        final List<Tag> initialTags = Arrays.asList(tags);
        if (!Utils.elementsAreUnique(initialTags)) {
            throw new DuplicateTagException();
        }
        internalList.addAll(initialTags);
    }

    /**
     * Constructs a tag list with the given tags.
     */
    public UniqueTagList(Collection<Tag> tags) throws DuplicateTagException {
        if (!Utils.elementsAreUnique(tags)) {
            throw new DuplicateTagException();
        }
        internalList.addAll(tags);
    }

    /**
     * Constructs a tag list with the given tags.
     */
    public UniqueTagList(Set<Tag> tags) {
        internalList.addAll(tags);
    }

    /**
     * Constructs a shallow copy of the given tag list.
     */
    public UniqueTagList(UniqueTagList source) {
        internalList.addAll(source.internalList);
    }

    /**
     * Returns a new Set that is a deep copy of all tags in this list.
     * This set is mutable and change-insulated against the internal list.
     */
    public Set<Tag> toSet() {
        return new HashSet<>(internalList);
    }

    /**
     * Returns true if the list contains an equivalent Tag as the given argument.
     */
    public boolean contains(Tag toCheck) {
        return internalList.contains(toCheck);
    }

    /**
     * Adds all the given tags to this list.
     *
     * @throws DuplicateTagException if the argument tag list contains tag(s) that already exist in this list.
     */
    public void addAll(UniqueTagList tags) throws DuplicateTagException {
        if (!Collections.disjoint(this.internalList, tags.internalList)) {
            throw new DuplicateTagException();
        }
        this.internalList.addAll(tags.internalList);
    }

    /**
     * Adds every tag from the argument list that does not yet exist in this list.
     */
    public void mergeFrom(UniqueTagList tags) {
        final Set<Tag> alreadyInside = this.toSet();
        for (Tag tag : tags) {
            if (!alreadyInside.contains(tag)) {
                internalList.add(tag);
            }
        }
    }

    /**
     * Clears all tags in list.
     */
    public void clear() {
        internalList.clear();
    }

    /**
     * Replaces the Tags in this list with those in the argument tag list.
     */
    public void setTags(UniqueTagList replacement) {
        this.internalList.clear();
        this.internalList.addAll(replacement.internalList);
    }

    @Override
    public Iterator<Tag> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UniqueTagList // instanceof handles nulls
                        && this.internalList.equals(((UniqueTagList) other).internalList));
    }

}
