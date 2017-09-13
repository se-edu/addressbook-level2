package seedu.addressbook.data.person;

import java.util.Comparator;

public class PersonNameComparator implements Comparator<Person> {
    public PersonNameComparator() {
    }

    public int compare(Person var1, Person var2) {
        return var1.getName().toString().compareTo(var2.getName().toString());
    }
}
