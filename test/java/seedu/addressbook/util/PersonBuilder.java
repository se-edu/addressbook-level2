package seedu.addressbook.util;

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
    
    public PersonBuilder withPrivatePhone(String phone) throws IllegalValueException {
        this.person.setPhone(new Phone(phone, true));
        return this;
    }
    
    public PersonBuilder withPublicPhone(String phone) throws IllegalValueException {
        this.person.setPhone(new Phone(phone, false));
        return this;
    }

    public PersonBuilder withPrivateEmail(String email) throws IllegalValueException {
        this.person.setEmail(new Email(email, true));
        return this;
    }

    public PersonBuilder withPublicEmail(String email) throws IllegalValueException {
        this.person.setEmail(new Email(email, false));
        return this;
    }
    
    public PersonBuilder withPrivateAddress(String address) throws IllegalValueException {
        this.person.setAddress(new Address(address, true));
        return this;
    }

    public PersonBuilder withPublicAddress(String address) throws IllegalValueException {
        this.person.setAddress(new Address(address, false));
        return this;
    }

    public TestPerson build() {
        return this.person;
    }
}
