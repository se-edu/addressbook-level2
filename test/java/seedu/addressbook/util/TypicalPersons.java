package seedu.addressbook.util;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

/**
 * Class to generate typical test persons
 */
public class TypicalPersons {

    public static Person amy, bill, candy, donald, grace;

    public TypicalPersons() {
        try {
            amy = new PersonBuilder().withName("Amy Buck").withPublicPhone("91119111")
                    .withPublicEmail("ab@gmail.com").withPublicAddress("1 Clementi Road").build();

            bill = new PersonBuilder().withName("Bill Clint").withPublicPhone("92229222")
                    .withPublicEmail("bc@gmail.com").withPrivateAddress("2 Clementi Road").build();

            candy = new PersonBuilder().withName("Candy Destiny").withPrivatePhone("93339333")
                    .withPublicEmail("cd@gmail.com").withPublicAddress("3 Clementi Road").build();

            donald = new PersonBuilder().withName("Donald Eillot").withPrivatePhone("94449444")
                    .withPrivateEmail("de@gmail.com").withPublicAddress("4 Clementi Road").build();

            grace = new PersonBuilder().withName("Grace Hopper").withPublicPhone("95559555")
                    .withPrivateEmail("gh@gmail.com").withPrivateAddress("5 Clementi Road").build();

        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public void loadAddressBookWithSampleData(AddressBook ab) {
        try {
            for (Person t : getTypicalPersons()) {
                ab.addPerson(new Person(t));
            }
        } catch (UniquePersonList.DuplicatePersonException e) {
            assert false : "not possible";
        }
    }

    public Person[] getTypicalPersons() {
        return new Person[] {amy, bill, candy, donald, grace};
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}
