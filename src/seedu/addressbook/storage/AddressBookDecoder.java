package seedu.addressbook.storage;

import static seedu.addressbook.parser.Parser.PERSON_DATA_ARGS_FORMAT;

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
 * Decodes the storage data file into an {@code AddressBook} object.
 */
public class AddressBookDecoder {

    /**
     * Decodes {@code encodedAddressBook} into an {@code AddressBook} containing the decoded persons.
     *
     * @throws IllegalValueException if any of the fields in any encoded person string is invalid.
     * @throws StorageOperationException if the {@code encodedAddressBook} is in an invalid format.
     */
    public static AddressBook decodeAddressBook(List<String> encodedAddressBook)
            throws IllegalValueException, StorageOperationException {
        final List<Person> decodedPersons = new ArrayList<>();
        for (String encodedPerson : encodedAddressBook) {
            decodedPersons.add(decodePersonFromString(encodedPerson));
        }
        return new AddressBook(new UniquePersonList(decodedPersons));
    }

    /**
     * Decodes {@code encodedPerson} into a {@code Person}.
     *
     * @throws IllegalValueException if any field in the {@code encodedPerson} is invalid.
     * @throws StorageOperationException if {@code encodedPerson} is in an invalid format.
     */
    private static Person decodePersonFromString(String encodedPerson)
            throws IllegalValueException, StorageOperationException {
        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(encodedPerson);
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
     * Returns true if {@code matchedPrefix} is equal to the private prefix for contact details.
     */
    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return "p".equals(matchedPrefix);
    }

    /**
     * Extracts the {@code Tag}s from the {@code tagArguments} string.
     * Merges duplicate tag strings.
     */
    private static Set<Tag> getTagsFromEncodedPerson(String tagArguments) throws IllegalValueException {
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
