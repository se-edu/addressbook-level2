package seedu.addressbook.util;

import seedu.addressbook.common.Utils;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * Factory class for building TestPerson
 */
public class PersonBuilder {

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;

    public PersonBuilder() {
        this.name = null;
        this.phone = null;
        this.email = null;
        this.address = null;
    }

    public PersonBuilder withName(String name) throws IllegalValueException {
        this.name = new Name(name);
        return this;
    }
    
    public PersonBuilder withPrivatePhone(String phone) throws IllegalValueException {
        this.phone = new Phone(phone, true);
        return this;
    }
    
    public PersonBuilder withPublicPhone(String phone) throws IllegalValueException {
        this.phone = new Phone(phone, false);
        return this;
    }

    public PersonBuilder withPrivateEmail(String email) throws IllegalValueException {
        this.email = new Email(email, true);
        return this;
    }

    public PersonBuilder withPublicEmail(String email) throws IllegalValueException {
        this.email = new Email(email, false);
        return this;
    }
    
    public PersonBuilder withPrivateAddress(String address) throws IllegalValueException {
        this.address = new Address(address, true);
        return this;
    }

    public PersonBuilder withPublicAddress(String address) throws IllegalValueException {
        this.address = new Address(address, false);
        return this;
    }

    public Person build() {
        assert !Utils.isAnyNull(this.name, this.phone, this.email, this.address);
        return new Person(this.name, this.phone, this.email, this.address, new UniqueTagList());
    }
}
