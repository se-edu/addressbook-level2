package seedu.addressbook.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.addressbook.util.TestUtil.assertFileDoesNotExist;
import static seedu.addressbook.util.TestUtil.assertTextFilesEqual;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.storage.StorageFile.StorageOperationException;

public class StorageFileTest {
    @TempDir
    public static Path testFolder;

    private static final String TEST_DATA_FOLDER = "test/data/StorageFileTest";
    private static final String NON_EXISTANT_FILE_NAME = "ThisFileDoesNotExist.txt";

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        assertThrows(NullPointerException.class, () -> new StorageFile(null));
    }

    @Test
    public void constructor_noTxtExtension_exceptionThrown() throws Exception {
        assertThrows(IllegalValueException.class, () ->
                new StorageFile(TEST_DATA_FOLDER + "/" + "InvalidfileName"));
    }

    @Test
    public void load_invalidFormat_exceptionThrown() throws Exception {
        // The file contains valid txt data, but does not match the Person format
        StorageFile storage = getStorage("InvalidData.txt");
        assertThrows(StorageOperationException.class, () -> storage.load());
    }

    @Test
    public void load_validFormat() throws Exception {
        AddressBook actualAb = getStorage("ValidData.txt").load();
        AddressBook expectedAb = getTestAddressBook();

        // ensure loaded AddressBook is properly constructed with test data
        // TODO: overwrite equals method in AddressBook class and replace with equals method below
        assertEquals(actualAb.getAllPersons(), expectedAb.getAllPersons());
    }

    @Test
    public void load_nonExistantFile_returnsEmptyAddressBook() throws Exception {
        AddressBook actualAb = getStorage(NON_EXISTANT_FILE_NAME).load();
        AddressBook expectedAb = new AddressBook();

        assertEquals(actualAb, expectedAb);

        // verify that loading does not result in the file being created
        assertFileDoesNotExist(TEST_DATA_FOLDER + "/" + NON_EXISTANT_FILE_NAME);
    }

    @Test
    public void save_nullAddressBook_exceptionThrown() throws Exception {
        StorageFile storage = getTempStorage();
        assertThrows(NullPointerException.class, () -> storage.save(null));
    }

    @Test
    public void save_validAddressBook() throws Exception {
        AddressBook ab = getTestAddressBook();
        StorageFile storage = getTempStorage();
        storage.save(ab);

        assertStorageFilesEqual(storage, getStorage("ValidData.txt"));
    }

    // getPath() method in StorageFile class is trivial so it is not tested

    /**
     * Asserts that the contents of two storage files are the same.
     */
    private void assertStorageFilesEqual(StorageFile sf1, StorageFile sf2) throws Exception {
        assertTextFilesEqual(Paths.get(sf1.getPath()), Paths.get(sf2.getPath()));
    }

    private StorageFile getStorage(String fileName) throws Exception {
        return new StorageFile(TEST_DATA_FOLDER + "/" + fileName);
    }

    private StorageFile getTempStorage() throws Exception {
        return new StorageFile(testFolder.resolve("temp.txt").toString());
    }

    private AddressBook getTestAddressBook() throws Exception {
        AddressBook ab = new AddressBook();
        ab.addPerson(new Person(new Name("John Doe"),
                                new Phone("98765432", false),
                                new Email("johnd@gmail.com", false),
                                new Address("John street, block 123, #01-01", false),
                                Collections.emptySet()));
        ab.addPerson(new Person(new Name("Betsy Crowe"),
                                new Phone("1234567", true),
                                new Email("betsycrowe@gmail.com", false),
                                new Address("Newgate Prison", true),
                                new HashSet<>(Arrays.asList(new Tag("friend"), new Tag("criminal")))));
        return ab;
    }
}
