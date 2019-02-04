package seedu.addressbook.data.person;

import java.util.Comparator;

/**
 *
 * Comparator class will be supplied into Collections.sort
 * it will sort all Person object in AddressBook by name in
 * alphabetical order only
 */

public class PersonSorter implements Comparator<Person> {

    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().fullName.toLowerCase().compareTo(
                p2.getName().fullName.toLowerCase()
        );
    }
}
