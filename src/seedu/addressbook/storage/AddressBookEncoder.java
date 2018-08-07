package seedu.addressbook.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.tag.Tag;

/**
 * Encodes the AddressBook object into a data file for storage.
 */
public class AddressBookEncoder {

    /**
     * Encodes all the Persons in the AddressBook to the data file for storage.
     *
     * @param storageFilePath file for saving
     * @throws IOException if there is an error saving to file.
     */
    public static void encodeAddressBookToFile(AddressBook toSave, Path storageFilePath) throws IOException {
        final List<String> linesToWrite = encodePersonsToStrings(toSave.getAllPersons());
        Files.write(storageFilePath, linesToWrite);
    }

    /**
     * Encodes persons into list of decodable and readable string representations.
     *
     * @param persons to be encoded
     * @return encoded strings
     */
    private static List<String> encodePersonsToStrings(UniquePersonList persons) {
        final List<String> encodedPersons = new ArrayList<>();
        for (Person person : persons) {
            encodedPersons.add(encodePersonToString(person));
        }
        return encodedPersons;
    }

    /**
     * Encodes a person into a decodable and readable string representation.
     *
     * @param person to be encoded
     * @return encoded string
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

        for (Tag tag : person.getTags()) {
            encodedPersonBuilder.append(" t/").append(tag.tagName);
        }

        return encodedPersonBuilder.toString();
    }
}
