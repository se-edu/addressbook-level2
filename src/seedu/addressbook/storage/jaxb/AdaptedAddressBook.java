package seedu.addressbook.storage.jaxb;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * JAXB-friendly adapted address book data holder class.
 */
@XmlRootElement(name = "AddressBook")
public class AdaptedAddressBook {

    @XmlElement
    private List<AdaptedPerson> persons = new ArrayList<>();
    @XmlElement
    private List<AdaptedTag> tags = new ArrayList<>();

    /**
     * No-arg constructor for JAXB use.
     */
    public AdaptedAddressBook() {}

    /**
     * Converts a given AddressBook into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedAddressBook
     */
    public AdaptedAddressBook(AddressBook source) {
        persons = new ArrayList<>();
        tags = new ArrayList<>();
        for (ReadOnlyPerson person : source.getAllPersons()) {
            persons.add(new AdaptedPerson(person));
        }
        for (Tag tag : source.getAllTags()) {
            tags.add(new AdaptedTag(tag));
        }
    }


    /**
     * Returns true if any required field is missing.
     *
     * JAXB does not enforce (required = true) without a given XML schema.
     * Since we do most of our validation using the data class constructors, the only extra logic we need
     * is to ensure that every xml element in the document is present. JAXB sets missing elements as null,
     * so we check for that.
     */
    public boolean isAnyRequiredFieldMissing() {
        for (AdaptedTag tag : tags) {
            if (tag.isAnyRequiredFieldMissing()) {
                return true;
            }
        }
        for (AdaptedPerson person : persons) {
            if (person.isAnyRequiredFieldMissing()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Converts this jaxb-friendly {@code AdaptedAddressBook} object into the corresponding(@code AddressBook} object.
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public AddressBook toModelType() throws IllegalValueException {
        final List<Tag> tagList = new ArrayList<>();
        final List<Person> personList = new ArrayList<>();
        for (AdaptedTag tag : tags) {
            tagList.add(tag.toModelType());
        }
        for (AdaptedPerson person : persons) {
            personList.add(person.toModelType());
        }
        return new AddressBook(new UniquePersonList(personList), new UniqueTagList(tagList));
    }
}
