package seedu.addressbook;

import seedu.addressbook.model.AddressBook;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Convenience class for working with the storage file.
 */
public class StorageFile {

    public static class InvalidStorageFilePathException extends Exception {}

    public final Path path;

    public StorageFile(String filePath) throws InvalidStorageFilePathException {
        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException();
        }
    }

    /**
     * Tests if a string file path is acceptable as a storage file
     */
    public static boolean isValidPath(Path testPath) {
        return testPath.toString().endsWith(".txt");
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

    @Override
    public String toString() {
        return path.toString();
    }
}
