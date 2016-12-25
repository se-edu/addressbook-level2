package seedu.addressbook.util;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Person;

/**
 * Class to generate typical test persons
 */
public class TypicalPersons {

    public static Person amy() throws IllegalValueException {
        return new PersonBuilder().withName("Amy Buck").withPublicPhone("91119111")
                .withPublicEmail("ab@gmail.com").withPublicAddress("1 Clementi Road").build();
    }
    
    public static Person bill() throws IllegalValueException {
        return new PersonBuilder().withName("Bill Clint").withPublicPhone("92229222")
                .withPublicEmail("bc@gmail.com").withPrivateAddress("2 Clementi Road").build();
    }
    
    public static Person candy() throws IllegalValueException {
        return new PersonBuilder().withName("Candy Destiny").withPrivatePhone("93339333")
                .withPublicEmail("cd@gmail.com").withPublicAddress("3 Clementi Road").build();
    }
   
    private void loadAddressBookWithSampleData(AddressBook ab) {        
        try {
            Person[] sampleData = new Person[] {amy(), bill(), candy()};
            for (Person p : sampleData) {
                ab.addPerson(new Person(p));
            }
        } catch (IllegalValueException e) {
            assert false : "not possible";
        } 
    }

    public AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        loadAddressBookWithSampleData(ab);
        return ab;
    }
}
