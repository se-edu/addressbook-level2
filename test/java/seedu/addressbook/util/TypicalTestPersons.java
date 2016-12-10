package seedu.addressbook.util;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

/**
 * Class to generate typical test persons
 */
public class TypicalTestPersons {

    public static TestPerson amy, bill, candy, donald, grace;

    public TypicalTestPersons() {
        try {
            amy = new PersonBuilder().withName("Amy Buck").withPublicPhone("91234567")
                    .withPublicEmail("cs@gmail.com").withPublicAddress("1 Clementi Road").build();

            bill = new PersonBuilder().withName("Bill Clint").withPublicPhone("92434567")
                    .withPublicEmail("bg@gmail.com").withPrivateAddress("2 Clementi Road").build();

            candy = new PersonBuilder().withName("Candy Destiny").withPrivatePhone("91934267")
                    .withPublicEmail("sj@gmail.com").withPublicAddress("3 Clementi Road").build();

            donald = new PersonBuilder().withName("Donald Eillot").withPrivatePhone("91214567")
                    .withPrivateEmail("dk@gmail.com").withPublicAddress("4 Clementi Road").build();

            grace = new PersonBuilder().withName("Grace Hopper").withPublicPhone("83434567")
                    .withPrivateEmail("gh@gmail.com").withPrivateAddress("5 Clementi Road").build();

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
        return new TestPerson[] {amy, bill, candy, donald, grace};
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}
