package seedu.addressbook.data.person;

import java.util.Comparator;

/**
 *
 * sort addressBook by Name in Alphebetical Order
 */

public class SortPeople implements Comparator<Person> {

    @Override
    public int compare(Person person1, Person person2) {
        return person1.getName().fullName.compareTo(person2.getName().fullName);
    }
}