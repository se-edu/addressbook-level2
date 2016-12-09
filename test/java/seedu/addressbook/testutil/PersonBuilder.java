package seedu.addressbook.testutil;

import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Phone;

/**
 * Factory class for building TestPerson
 */
public class PersonBuilder {

    private TestPerson person;

    public PersonBuilder() {
        this.person = new TestPerson();
    }

    public PersonBuilder withName(String name) throws IllegalValueException {
        this.person.setName(new Name(name));
        return this;
    }

    public PersonBuilder withPhone(String phone, boolean isPrivate) throws IllegalValueException {
        this.person.setPhone(new Phone(phone, isPrivate));
        return this;
    }

    public PersonBuilder withEmail(String email, boolean isPrivate) throws IllegalValueException {
        this.person.setEmail(new Email(email, isPrivate));
        return this;
    }

    public PersonBuilder withAddress(String address, boolean isPrivate) throws IllegalValueException {
        this.person.setAddress(new Address(address, isPrivate));
        return this;
    }

    public TestPerson build() {
        return this.person;
    }
}
