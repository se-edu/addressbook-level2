package seedu.addressbook.util;

import java.util.Collections;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.Tag;

/**
 * Class to generate typical test persons
 */
public class TypicalPersons {

    private Person amy;
    private Person bill;
    private Person candy;
    private Person dan;

    public TypicalPersons() {
        try {
            amy = new Person(new Name("Amy Buck"), new Phone("91119111", false), new Email("ab@gmail.com", false),
                    new Address("1 Clementi Road", false), Collections.emptySet());
            bill = new Person(new Name("Bill Clint"), new Phone("92229222", false), new Email("bc@gmail.com", false),
                    new Address("2 Clementi Road", true), Collections.emptySet());
            candy = new Person(new Name("Candy Destiny"), new Phone("93339333", true),
                    new Email("cd@gmail.com", false), new Address("3 Clementi Road", true), Collections.emptySet());
            dan = new Person(new Name("Dan Smith"), new Phone("1234556", true), new Email("ss@tt.com", true),
                    new Address("NUS", true), Collections.singleton(new Tag("test")));
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    /**
     * Inserts predefined {@code Person} objects into a given instance of {@code AddressBook}
     * @param ab AddressBook in which {@code Person} objects will be added.
     */
    private void loadAddressBookWithSampleData(AddressBook ab) {
        try {
            for (Person p : this.getTypicalPersons()) {
                ab.addPerson(new Person(p));
            }
        } catch (IllegalValueException e) {
            assert false : "not possible";
        }
    }

    public Person[] getTypicalPersons() {
        return new Person[]{amy, bill, candy, dan};
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }

    public Person getAmy() {
        return amy;
    }

    public Person getBill() {
        return bill;
    }

    public Person getCandy() {
        return candy;
    }

    public Person getDan() {
        return dan;
    }
}
