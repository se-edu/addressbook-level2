package seedu.addressbook.model;

import seedu.addressbook.model.person.Person;
import seedu.addressbook.model.person.ReadOnlyPerson;
import seedu.addressbook.model.person.UniquePersonList;
import seedu.addressbook.model.person.UniquePersonList.*;
import seedu.addressbook.model.tag.UniqueTagList;
import seedu.addressbook.model.tag.UniqueTagList.*;
import seedu.addressbook.model.tag.Tag;

import java.util.Set;

/**
 * Represents the entire address book, and maintains the master list of persons and tags.
 *
 * Guarantees: Every tag found in every person will also be found in the tag list.
 */
public class AddressBook {

    private final UniquePersonList allPersons;
    private final UniqueTagList allTags; // can contain tags not attached to any person

    /**
     * Empty constructor.
     */
    public AddressBook() {
        allPersons = new UniquePersonList();
        allTags = new UniqueTagList();
    }

    /**
     * Populating constructor.
     * Will update the tag list with any missing tags found in any person.
     *
     * @param persons external changes to this will not affect the address book
     * @param tags external changes to this will not affect the address book
     */
    public AddressBook(UniquePersonList persons, UniqueTagList tags) {
        final Set<Tag> givenTags = tags.toSet();
        this.allPersons = new UniquePersonList(persons);
        // ensure tag list contains every tag in each person
        for (ReadOnlyPerson p : getAllPersons()) {
            for (Tag t : p.getTags()) {
                givenTags.add(t);
            }
        }
        this.allTags = new UniqueTagList(givenTags);
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
     * Defensively copied UniquePersonList of all persons in the address book at the time of the call.
     */
    public UniquePersonList getAllPersons() {
        return new UniquePersonList(allPersons);
    }

    /**
     * Defensively copied UniqueTagList of all tags in the address book at the time of the call.
     */
    public UniqueTagList getAllTags() {
        return new UniqueTagList(allTags);
    }
}
