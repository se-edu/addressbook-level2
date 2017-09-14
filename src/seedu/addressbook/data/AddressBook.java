package seedu.addressbook.data;

import java.util.*;

import seedu.addressbook.commands.AddCommand;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.person.UniquePersonList.DuplicatePersonException;
import seedu.addressbook.data.person.UniquePersonList.PersonNotFoundException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * Represents the entire address book. Contains the data of the address book.
 *
 * Guarantees:
 *  - Every tag found in every person will also be found in the tag list.
 *  - The tags in each person point to tag objects in the master list. (== equality)
 */
public class AddressBook {

    private final UniquePersonList allPersons;
    private final UniqueTagList allTags; // can contain tags not attached to any person
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final char INPUT_COMMENT_MARKER = '#';
    private static final String LINE_PREFIX = "|| ";
    /**
     * Creates an empty address book.
     */
    public AddressBook() {
        allPersons = new UniquePersonList();
        allTags = new UniqueTagList();
    }

    /**
     * Constructs an address book with the given data.
     * Also updates the tag list with any missing tags found in any person.
     *
     * @param persons external changes to this will not affect this address book
     * @param tags external changes to this will not affect this address book
     */
    public AddressBook(UniquePersonList persons, UniqueTagList tags) {
        this.allPersons = new UniquePersonList(persons);
        this.allTags = new UniqueTagList(tags);
        for (Person p : allPersons) {
            syncTagsWithMasterList(p);
        }
    }

    /**
     * Ensures that every tag in this person:
     *  - exists in the master list {@link #allTags}
     *  - points to a Tag object in the master list
     */
    private void syncTagsWithMasterList(Person person) {
        final UniqueTagList personTags = person.getTags();
        allTags.mergeFrom(personTags);

        // Create map with values = tag object references in the master list
        final Map<Tag, Tag> masterTagObjects = new HashMap<>();
        for (Tag tag : allTags) {
            masterTagObjects.put(tag, tag);
        }

        // Rebuild the list of person tags using references from the master list
        final Set<Tag> commonTagReferences = new HashSet<>();
        for (Tag tag : personTags) {
            commonTagReferences.add(masterTagObjects.get(tag));
        }
        person.setTags(new UniqueTagList(commonTagReferences));
    }

    /**
     * Adds a person to the address book.
     * Also checks the new person's tags and updates {@link #allTags} with any new tags found,
     * and updates the Tag objects in the person to point to those in {@link #allTags}.
     *
     * @throws DuplicatePersonException if an equivalent person already exists.
     */
    public void addPerson(Person toAdd) throws DuplicatePersonException {
        allPersons.add(toAdd);
        syncTagsWithMasterList(toAdd);
    }

    /**
     * Returns true if an equivalent person exists in the address book.
     */
    public boolean containsPerson(ReadOnlyPerson key) {
        return allPersons.contains(key);
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
     * Removes the equivalent person from the address book.
     *
     * @throws PersonNotFoundException if no such Person could be found.
     */
    public void UpdatePerson(ReadOnlyPerson toRemove) throws PersonNotFoundException {

        Person toUpdate = new Person(toRemove);
        ChooseUpdateMethod(SelectUpdateAction(), toUpdate );

    }

    public static String SelectUpdateAction() {

        System.out.println("|| " + "Enter 1 to update name , 2 to update phone no and 3 to update email: ");
        String inputLine = SCANNER.nextLine();
        // silently consume all blank and comment lines
        while (inputLine.trim().isEmpty() || inputLine.trim().charAt(0) == '#') {
            inputLine = SCANNER.nextLine();
        }
        System.out.println("|| " +"[Action chosen:" + inputLine + "]");
        return inputLine;
    }

    private static void ChooseUpdateMethod(String inputLine , Person toUpdate ) {


        switch (inputLine) {
            case "1":
                System.out.println(LINE_PREFIX +" Enter new name ");
                String newname = SCANNER.nextLine();
                // silently consume all blank and comment lines
                while (newname.trim().isEmpty() || newname.trim().charAt(0) == INPUT_COMMENT_MARKER) {
                    newname = SCANNER.nextLine();
                }
                toUpdate.name.fullName = newname;
                System.out.println(LINE_PREFIX +" The name has been replaced with " + toUpdate.name.fullName);

                break;

            case "2":
                System.out.println(LINE_PREFIX +" Enter new phone no ");
                String newphone = SCANNER.nextLine();
                // silently consume all blank and comment lines
                while (newphone.trim().isEmpty() || newphone.trim().charAt(0) == INPUT_COMMENT_MARKER) {
                    newphone = SCANNER.nextLine();
                }
                toUpdate.phone.value = newphone;
                System.out.println(LINE_PREFIX +" The phone has been replaced with " + toUpdate.phone.value);
                break;

            case "3":
                System.out.println(LINE_PREFIX +" Enter new email without prefix ");
                String newemail = SCANNER.nextLine();
                // silently consume all blank and comment lines
                while (newemail.trim().isEmpty() || newemail.trim().charAt(0) == INPUT_COMMENT_MARKER) {
                    newemail = SCANNER.nextLine();
                }

                toUpdate.email.value = newemail;
                System.out.println(LINE_PREFIX +" The email has been replaced with " + toUpdate.email.value);
                break;

            default:
                ChooseUpdateMethod(SelectUpdateAction(), toUpdate);
                break;
        }
    }


        /**
         * Clears all persons and tags from the address book.
         */
    public void clear() {
        allPersons.clear();
        allTags.clear();
    }

    /**
     * Returns a new UniquePersonList of all persons in the address book at the time of the call.
     */
    public UniquePersonList getAllPersons() {
        return new UniquePersonList(allPersons);
    }

    /**
     * Returns a new UniqueTagList of all tags in the address book at the time of the call.
     */
    public UniqueTagList getAllTags() {
        return new UniqueTagList(allTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddressBook // instanceof handles nulls
                        && this.allPersons.equals(((AddressBook) other).allPersons)
                        && this.allTags.equals(((AddressBook) other).allTags));
    }
}
