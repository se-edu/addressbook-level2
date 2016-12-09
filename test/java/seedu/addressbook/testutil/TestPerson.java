package seedu.addressbook.testutil;

import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.UniqueTagList;

public class TestPerson implements ReadOnlyPerson {
	
	private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    
    private final UniqueTagList tags;
    
    public TestPerson() {
    	tags = new UniqueTagList();
    }
    
    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }
    
    public void setName(Name name) {
        this.name = name;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public void setEmail(Email email) {
        this.email = email;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
   

    public UniqueTagList getTags() {
        return new UniqueTagList(tags);
    }
    
    
    
}
