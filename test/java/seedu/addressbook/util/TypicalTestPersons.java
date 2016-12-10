package seedu.addressbook.testutil;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

/**
 * Class to generate typical test persons
 */
public class TypicalTestPersons {

    public static TestPerson claude, gates, jobs, donald, hopper;

    public TypicalTestPersons() {
        try {
            claude = new PersonBuilder().withName("Claude Shannon").withPhone("91234567", false)
                    .withEmail("cs@gmail.com", false).withAddress("1 Clementi Road", true).build();

            gates = new PersonBuilder().withName("Bill Gates").withPhone("92434567", false)
                    .withEmail("bg@gmail.com", false).withAddress("2 Clementi Road", false).build();

            jobs = new PersonBuilder().withName("Steve Jobs").withPhone("91934267", true)
                    .withEmail("sj@gmail.com", true).withAddress("3 Clementi Road", false).build();

            donald = new PersonBuilder().withName("Donald Knuth").withPhone("91214567", true)
                    .withEmail("dk@gmail.com", false).withAddress("4 Clementi Road", true).build();

            hopper = new PersonBuilder().withName("Grace Hopper").withPhone("83434567", true)
                    .withEmail("gh@gmail.com", true).withAddress("5 Clementi Road", true).build();

        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public void loadAddressBookWithSampleData(AddressBook ab) {
        try {
            for (TestPerson t : getTypicalPersons()) {
                ab.addPerson(new Person(t));
            }
        } catch (UniquePersonList.DuplicatePersonException e) {
            assert false : "not possible";
        }
    }

    public TestPerson[] getTypicalPersons() {
        return new TestPerson[] { claude, gates, jobs, donald, hopper };
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}
