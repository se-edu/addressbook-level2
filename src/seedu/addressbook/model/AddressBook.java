package seedu.addressbook.model;

import seedu.addressbook.model.person.Person;
import seedu.addressbook.model.person.ReadOnlyPerson;
import seedu.addressbook.model.person.UniquePersonList;
import seedu.addressbook.model.person.UniquePersonList.*;
import seedu.addressbook.model.UniqueTagList.*;

import java.util.List;

/**
 * Represents the entire address book, and maintains the master list of persons and tags.
 *
 * Guarantees: Every tag found in every person will also be in the tag list.
 */
public class AddressBook {

    private final UniquePersonList allPersons;
    private final UniqueTagList allTags; // can contain tags not attached to any person

    public AddressBook() {
        allPersons = new UniquePersonList();
        allTags = new UniqueTagList();
    }

    /**
     * Adds a person to the address book.
     * Also checks the new person's tags and updates {@link #allTags} with any new tags found.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(Person toAdd) throws DuplicatePersonException {
        allPersons.add(toAdd);
        for (Tag tag : toAdd.getTags()) {
            try {
                // add tag to master tag list if not already inside
                if (!containsTag(tag)) {
                    addTag(tag);
                }
            } catch (DuplicateTagException dte) {
                throw new IllegalStateException(); // should never happen.
            }
        }
    }

    /**
     * Adds a tag to the list of tags present in the address book.
     *
     * @throws DuplicateTagException if an equivalent tag already exists.
     */
    public void addTag(Tag toAdd) throws DuplicateTagException {
        allTags.add(toAdd);
    }

    /**
     * Checks if an equivalent person exists in the address book.
     */
    public boolean containsPerson(ReadOnlyPerson key) {
        return allPersons.contains(key);
    }

    /**
     * Checks if an equivalent person exists in the address book.
     */
    public boolean containsTag(Tag key) {
        return allTags.contains(key);
    }

    /**
     * Removes the equivalent person from the address book.
     *
     * @throws PersonNotFoundException if no such Person could be found.
     */
    public void removePerson(ReadOnlyPerson toRemove) throws PersonNotFoundException {
        allPersons.remove(toRemove);
    }

    /**
     * Removes the equivalent Tag from the address book.
     *
     * @throws TagNotFoundException if no such Tag could be found.
     */
    public void removeTag(Tag toRemove) throws TagNotFoundException {
        allTags.remove(toRemove);
    }

    /**
     * Clears all persons and tags from the address book.
     */
    public void clear() {
        allPersons.clear();
        allTags.clear();
    }
    /**
     * Unmodifiable java List view of the persons in this address book cast as immutable {@link ReadOnlyPerson}s.
     * For use with other methods/libraries.
     * Any changes to the address book are immediately visible in the returned list.
     */
    public List<ReadOnlyPerson> getAllPersonsImmutableView() {
        return allPersons.immutableListView();
    }
}
