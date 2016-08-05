package seedu.addressbook;

import seedu.addressbook.model.AddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Convenience class for working with the storage file.
 */
public class StorageFile {

    private final Path path;

    public StorageFile(String filePath) {
        path = Paths.get(filePath);
    }

    /**
     * Creates this storage file if it is missing on the filesystem.
     *
     * @return true if the file was missing and successfully created
     * @throws IOException if unable to create the file.
     */
    public boolean createFileIfMissing() throws IOException {
        return path.toFile().createNewFile();
    }

    /**
     * Saves all data to this storage file.
     *
     * @throws IOException of there is an error saving to file.
     */
    public void saveAddressBookToFile(AddressBook addressBook) throws IOException {
        // TODO
    }

    /**
     * Loads data from this storage file.
     *
     * @throws IOException if there is an error reading from file.
     */
    public AddressBook loadAddressBookFromFile() throws IOException {
        return new AddressBook(); // TODO
    }
}
