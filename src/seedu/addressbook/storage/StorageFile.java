package seedu.addressbook.storage;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.storage.jaxb.AdaptedAddressBook;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Represents the file used to store address book data.
 */
public class StorageFile {

    /** Default file path used if the user doesn't provide the file name. */
    public static final String DEFAULT_STORAGE_FILEPATH = "addressbook.xml";
    
    /** Error messages when an exception is thrown. */
    public static final String MESSAGE_ERROR_CONVERT_ADDRESSBOOK = "Error converting address book into storage format";
    public static final String MESSAGE_STORAGE_FILE_NOT_FOUND = "Storage File could not be found: %1$s";
    public static final String MESSAGE_ERROR_WRITING_TO_FILE = "Error writing to file: %1$s";
    public static final String MESSAGE_ERROR_PARSING_FILE_DATA = "Error parsing file data format";
    public static final String MESSAGE_MISSINGL_DATA_ELEMENTS = "File data missing some elements";
    public static final String MESSAGE_ILLEGAL_DATA_VALUES = "File contains illegal data values; data type constraints not met";
    /* Note: Note the use of nested classes below.
     * More info https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */

    /**
     * Signals that the given file path does not fulfill the storage filepath constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and read/write data between the application
     * and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    private final JAXBContext jaxbContext;

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException if the default path is invalid
     */
    public StorageFile() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException if the given file path is invalid
     */
    public StorageFile(String filePath) throws InvalidStorageFilePathException {
        try {
            jaxbContext = JAXBContext.newInstance(AdaptedAddressBook.class);
        } catch (JAXBException jaxbe) {
            throw new RuntimeException("jaxb initialisation error");
        }

        path = Paths.get(filePath);
        if (!isValidPath(path)) {
            throw new InvalidStorageFilePathException("Storage file should end with '.xml'");
        }
    }

    /**
     * Returns true if the given path is acceptable as a storage file.
     * The file path is considered acceptable if it ends with '.xml'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".xml");
    }
    
    /**
     * Check if the file from the given file path exist
     * @return true if file exist and false otherwise
     */
    private boolean isFileExist() {
        File file = path.toFile();
        return file.exists();
    }

    /**
     * Saves all data to this storage file.
     *
     * @throws FileNotFoundException if the file does not exist.
     * @throws StorageOperationException if there were errors converting and/or storing data to file.
     */
    public void save(AddressBook addressBook) throws StorageOperationException, FileNotFoundException {
        if (!isFileExist()) {
            throw new FileNotFoundException(String.format(MESSAGE_STORAGE_FILE_NOT_FOUND, path));
        }
        /* Note: Note the 'try with resource' statement below.
         * More info: https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
         */
        try (final Writer fileWriter = new BufferedWriter(new FileWriter(path.toFile()))) {

            final AdaptedAddressBook toSave = new AdaptedAddressBook(addressBook);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(toSave, fileWriter);

        } catch (IOException ioe) {
            throw new StorageOperationException(String.format(MESSAGE_ERROR_WRITING_TO_FILE, path));
        } catch (JAXBException jaxbe) {
            throw new StorageOperationException(MESSAGE_ERROR_CONVERT_ADDRESSBOOK);
        }
    }

    /**
     * Loads data from this storage file.
     *
     * @throws StorageOperationException if there were errors reading and/or converting data from file.
     */
    public AddressBook load() throws StorageOperationException, FileNotFoundException {
        try (final Reader fileReader = new BufferedReader(new FileReader(path.toFile()))) {

            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final AdaptedAddressBook loaded = (AdaptedAddressBook) unmarshaller.unmarshal(fileReader);
            // manual check for missing elements
            if (loaded.isAnyRequiredFieldMissing()) {
                throw new StorageOperationException(MESSAGE_MISSINGL_DATA_ELEMENTS);
            }
            return loaded.toModelType();

        /* Note: Here, we are using an exception to create the file if it is missing. However, we should minimize
         * using exceptions to facilitate normal paths of execution. If we consider the missing file as a 'normal'
         * situation (i.e. not truly exceptional) we should not use an exception to handle it.
         */

        // create empty file if not found
        } catch (FileNotFoundException fnfe) {
            final AddressBook empty = new AddressBook();
            save(empty);
            return empty;

        // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException(String.format(MESSAGE_ERROR_WRITING_TO_FILE, path));
        } catch (JAXBException jaxbe) {
            throw new StorageOperationException(MESSAGE_ERROR_PARSING_FILE_DATA);
        } catch (IllegalValueException ive) {
            throw new StorageOperationException(MESSAGE_ILLEGAL_DATA_VALUES);
        }
    }

    public String getPath() {
        return path.toString();
    }

}
