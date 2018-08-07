package seedu.addressbook.storage;

import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;

/**
 * Encodes the {@code AddressBook} object into a data file for storage.
 */
public class AddressBookEncoder {

    /**
     * Encodes all the {@code Person} in the {@code toSave} into a list of decodable and readable string presentation
     * for storage.
     */
    public static List<String> encodeAddressBook(AddressBook toSave) {
        final List<String> encodedPersons = new ArrayList<>();
        toSave.getAllPersons().forEach(person -> encodedPersons.add(encodePersonToString(person)));
        return encodedPersons;
    }

    /**
     * Encodes the {@code person} into a decodable and readable string representation.
     */
    private static String encodePersonToString(Person person) {
        final StringBuilder encodedPersonBuilder = new StringBuilder();

        encodedPersonBuilder.append(person.getName());

        encodedPersonBuilder.append(person.getPhone().isPrivate() ? " p" : " ");
        encodedPersonBuilder.append("p/").append(person.getPhone().value);

        encodedPersonBuilder.append(person.getEmail().isPrivate() ? " p" : " ");
        encodedPersonBuilder.append("e/").append(person.getEmail().value);

        encodedPersonBuilder.append(person.getAddress().isPrivate() ? " p" : " ");
        encodedPersonBuilder.append("a/").append(person.getAddress().value);

        person.getTags().forEach(tag -> encodedPersonBuilder.append(" t/").append(tag.tagName));

        return encodedPersonBuilder.toString();
    }
}
