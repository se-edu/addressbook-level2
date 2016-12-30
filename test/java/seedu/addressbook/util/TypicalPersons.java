package seedu.addressbook.util;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * Class to generate typical test persons
 */
public class TypicalPersons {

    public Person amy, bill, candy, dan;

    public TypicalPersons() {
        try {
            amy = new Person(new Name("Amy Buck"), new Phone("91119111", false), new Email("ab@gmail.com", false),
                    new Address("1 Clementi Road", false), new UniqueTagList());
            bill = new Person(new Name("Bill Clint"), new Phone("92229222", false), new Email("bc@gmail.com", true),
                    new Address("2 Clementi Road", false), new UniqueTagList(new Tag("bb")));
            candy = new Person(new Name("Candy Destiny"), new Phone("93339333", true), new Email("cd@gmail.com", false),
                    new Address("3 Clementi Road", true), new UniqueTagList(new Tag("a")));
            dan = new Person(new Name("Dan Smith"), new Phone("12312", true), new Email("d@b", true),
                    new Address("USA", true), new UniqueTagList(new Tag("c"), new Tag("D")));
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

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
        return new Person[] { amy, bill, candy };
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }

    public List<ReadOnlyPerson> getListWithAllPersons() {
        List<ReadOnlyPerson> list = new ArrayList<ReadOnlyPerson>();
        for (Person p : getTypicalPersons()) {
            list.add(new Person(p));
        }
        return list;
    }

    public List<ReadOnlyPerson> getListWithSomePersons() {
        List<ReadOnlyPerson> list = new ArrayList<ReadOnlyPerson>();
        for (Person p : getTypicalPersons()) {
            list.add(new Person(p));
        }
        list.remove(0);
        return list;
    }
}
