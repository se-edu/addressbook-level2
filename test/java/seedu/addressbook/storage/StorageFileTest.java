package seedu.addressbook.storage;

import static org.junit.Assert.assertEquals;
import java.nio.file.Paths;
import java.util.Collections;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.Address;
import seedu.addressbook.data.person.Email;
import seedu.addressbook.data.person.Name;
import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.Phone;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.storage.StorageFile.StorageOperationException;
import seedu.addressbook.util.TestUtil;

public class StorageFileTest {
    private static final String TEST_DATA_FOLDER = "test/data/StorageFileTest";

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void constructor_nullFilePath_exceptionThrown() throws Exception {
        thrown.expect(NullPointerException.class);
        new StorageFile(null);
    }

    @Test
    public void constructor_noTxtExtension_exceptionThrown() throws Exception {
        thrown.expect(IllegalValueException.class);
        new StorageFile(TEST_DATA_FOLDER + "/InvalidFileName");
    }

    @Test
    public void load_invalidFormat_exceptionThrown() throws Exception {
        // The file contains valid xml data, but does not match the AddressBook class
        StorageFile storage = getStorage("/InvalidData.txt");
        thrown.expect(StorageOperationException.class);
        storage.load();
    }

    @Test
    public void load_validFormat() throws Exception {
        AddressBook actualAB = getStorage("/ValidData.txt").load();
        AddressBook expectedAB = getTestAddressBook();

        // ensure loaded AddressBook Object is properly constructed with test data
        //TODO overwrite equals method in AddressBook class and replace with equals method below
        assertEquals(actualAB.getAllPersons(), expectedAB.getAllPersons());
    }

    @Test
    public void save_nullAddressBook_exceptionThrown() throws Exception {
        StorageFile storage = getStorage("/temp.txt");
        thrown.expect(NullPointerException.class);
        storage.save(null);
    }

    @Test
    public void save_validAddressBook() throws Exception {
        AddressBook ab = getTestAddressBook();

        getStorage("/temp.txt").save(ab);

        // ensure xml data for sample data is properly structured and saved
        assertSaveSuccess("temp.txt", "ValidData.txt");
    }

    // getPath() method in StorageFile class is trivial so it is not tested

    private void assertSaveSuccess(String file1, String file2) throws Exception {
        TestUtil.assertTextFilesEqual(Paths.get(TEST_DATA_FOLDER, file1), Paths.get(TEST_DATA_FOLDER, file2));
    }

    private StorageFile getStorage(String filename) throws Exception {
        return new StorageFile(TEST_DATA_FOLDER + filename);
    }

    private AddressBook getTestAddressBook() throws Exception {
        AddressBook ab = new AddressBook();
        ab.addPerson(new Person(new Name("John Doe"), 
                                new Phone("98765432", false),
                                new Email("johnd@gmail.com", false), 
                                new Address("John street, block 123, #01-01", false),
                                new UniqueTagList(Collections.emptySet())));
        ab.addPerson(new Person(new Name("Betsy Crowe"),
                                new Phone("1234567", true),
                                new Email("betsycrowe@gmail.com", false),
                                new Address("Newgate Prison", true),
                                new UniqueTagList(new Tag("friend"), new Tag("criminal"))));
        return ab;
    }
}
