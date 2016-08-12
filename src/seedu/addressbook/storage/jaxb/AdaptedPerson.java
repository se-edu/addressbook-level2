package seedu.addressbook.storage.jaxb;

import seedu.addressbook.model.IllegalValueException;
import seedu.addressbook.model.Tag;
import seedu.addressbook.model.UniqueTagList;
import seedu.addressbook.model.person.*;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.List;

/**
 * JAXB-friendly adapted person data holder class.
 */
public class AdaptedPerson {

    public static class AdaptedContactDetail {
        @XmlValue
        public String value;
        @XmlAttribute(required = true)
        public boolean isPrivate;
    }

    @XmlElement(required = true)
    public String name;
    @XmlElement(required = true)
    public AdaptedContactDetail phone;
    @XmlElement(required = true)
    public AdaptedContactDetail email;
    @XmlElement(required = true)
    public AdaptedContactDetail address;

    @XmlElement(required = true)
    public List<AdaptedTag> tagged;

    /**
     * No-arg constructor for JAXB use.
     */
    public AdaptedPerson() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedPerson
     */
    public AdaptedPerson(ReadOnlyPerson source) {
        name = source.getName().fullName;

        phone = new AdaptedContactDetail();
        phone.isPrivate = source.getPhone().isPrivate();
        phone.value = source.getPhone().value;

        email = new AdaptedContactDetail();
        email.isPrivate = source.getEmail().isPrivate();
        email.value = source.getEmail().value;

        address = new AdaptedContactDetail();
        address.isPrivate = source.getAddress().isPrivate();
        address.value = source.getAddress().value;

        tagged = new ArrayList<>();
        for (Tag tag : source.getTags()) {
            tagged.add(new AdaptedTag(tag));
        }
    }

    /**
     * Converts this jaxb-friendly adapted person object into the model's Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (AdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Name name = new Name(this.name);
        final Phone phone = new Phone(this.phone.value, this.phone.isPrivate);
        final Email email = new Email(this.email.value, this.email.isPrivate);
        final Address address = new Address(this.address.value, this.address.isPrivate);
        final UniqueTagList tags = new UniqueTagList(personTags);
        return new Person(name, phone, email, address, tags);
    }
}
