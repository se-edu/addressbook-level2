package seedu.addressbook.data.person;

import java.util.Comparator;

class PersonComparator implements Comparator<Person>{
    @Override
    public int compare(Person p1, Person p2) {
        return p1.getName().toString()
                .compareToIgnoreCase(p2.getName().toString());
    }
}
