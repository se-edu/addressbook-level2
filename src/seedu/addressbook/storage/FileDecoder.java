package seedu.addressbook.storage;

import static seedu.addressbook.parser.Parser.PERSON_DATA_ARGS_FORMAT;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

/**
 * Decodes the storage data file into an AddressBook object.
 */
public class FileDecoder {

    public static AddressBook decodeFileToAddressBook(Path dataFilePath)
            throws IOException, IllegalValueException, StorageOperationException {
        final List<Person> successfullyDecodedPersons = decodePersonsFromStrings(getLinesInFile(dataFilePath));
        return new AddressBook(new UniquePersonList(successfullyDecodedPersons));
    }

    /**
     * Gets all lines from the file as a list of strings. Line separators are removed.
     */
    private static List<String> getLinesInFile(Path dataFilePath) throws IOException {
        return new ArrayList<>(Files.readAllLines(dataFilePath));
    }

    /**
     * Decodes persons from a list of string representations.
     *
     * @param encodedPersons strings to be decoded
     * @return List containing all decoded persons
     * @throws IllegalValueException if any field is invalid
     * @throws StorageOperationException if cannot decode any
     */
    private static List<Person> decodePersonsFromStrings(List<String> encodedPersons)
            throws IllegalValueException, StorageOperationException {
        final List<Person> decodedPersons = new ArrayList<>();
        for (String encodedPerson : encodedPersons) {
            decodedPersons.add(decodePersonFromString(encodedPerson));
        }
        return decodedPersons;
    }

    /**
     * Decodes a person from it's supposed string representation.
     *
     * @param encodedPerson string to be decoded
     * @return decoded person
     * @throws IllegalValueException if any field is invalid
     * @throws StorageOperationException if encodedPerson cannot be decoded
     */
    private static Person decodePersonFromString(String encodedPerson)
            throws IllegalValueException, StorageOperationException {
        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(encodedPerson);
        // Validate encodedPerson string format
        if (!matcher.matches()) {
            throw new StorageOperationException("Encoded person in invalid format. Unable to decode.");
        }

        return new Person(
                new Name(matcher.group("name")),

                new Phone(matcher.group("phone"), isPrivatePrefixPresent(matcher.group("isPhonePrivate"))),

                new Email(matcher.group("email"), isPrivatePrefixPresent(matcher.group("isEmailPrivate"))),

                new Address(matcher.group("address"), isPrivatePrefixPresent(matcher.group("isAddressPrivate"))),

                getTagsFromEncodedPerson(matcher.group("tagArguments"))
        );
    }

    /**
     * Returns true if the private prefix is present for a contact detail in the encoded person's string.
     */
    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the person's tags from the encoded person's tag arguments string.
     * Merges duplicate tag strings.
     */
    private static Set<Tag> getTagsFromEncodedPerson(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }

        // replace first delimiter prefix, then split
        final String[] tagStrings = tagArguments.replaceFirst(" t/", "").split(" t/");
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tagStrings) {
            tagSet.add(new Tag(tagName));
        }

        return tagSet;
    }
}
