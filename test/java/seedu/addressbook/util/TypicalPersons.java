package seedu.addressbook.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 *
 */
public class TypicalPersons {

    public static Person johnDoe, selbyWhite, betsyCrowe, steveSam;
    
    private static void initialisePersons() {
        try {
            johnDoe = new Person(new Name("John Doe"),
                                 new Phone("98765432", false),
                                 new Email("johnd@gmail.com", false),
                                 new Address("John street, block 123, #01-01", false),
                                 new UniqueTagList(Collections.emptySet()));
            selbyWhite = new Person(new Name("Selby White"),
                                    new Phone("111344", false),
                                    new Email("me@haoo.com", true),
                                    new Address("UK", false),
                                    new UniqueTagList(Collections.emptySet()));
            betsyCrowe = new Person(new Name("Betsy Crowe"),
                                    new Phone("1234567", true),
                                    new Email("betsycrowe@gmail.com", false),
                                    new Address("Newgate Prison", true),
                                    new UniqueTagList(new Tag("friend"), new Tag("criminal")));
            steveSam = new Person(new Name("Steve Sam"),
                                  new Phone("1231", true),
                                  new Email("ab@apple.com", true),
                                  new Address("Silicon Vally", true),
                                  new UniqueTagList(new Tag("boss")));
        } catch (IllegalValueException e) {
            e.printStackTrace();
            assert false : "not possible";
        }
    }

    public static void loadAddressBookWithSampleData(AddressBook ab) {
        try {
            initialisePersons();
            ab.addPerson(johnDoe);
            ab.addPerson(selbyWhite);
            ab.addPerson(betsyCrowe);
            ab.addPerson(steveSam);
        } catch (UniquePersonList.DuplicatePersonException e) {
            assert false : "not possible";
        }
    }
    
    public static List<ReadOnlyPerson> getListWithAllPersons() {
        List<ReadOnlyPerson> list = new ArrayList<ReadOnlyPerson>();
        list.add(johnDoe);
        list.add(selbyWhite);
        list.add(betsyCrowe);
        list.add(steveSam);
        return list;
    }
    
    public static List<ReadOnlyPerson> getListWithSomePersons() {
        List<ReadOnlyPerson> list = new ArrayList<ReadOnlyPerson>();
        list.add(johnDoe);
        list.add(selbyWhite);
        list.add(steveSam);
        return list;
    }
}
