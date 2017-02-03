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

    private static final String MESSAGE_FILE_NOT_FOUND = "|| " + "Save file not found, saving a new file";

    /*
     * Note: Note the use of nested classes below. More info
     * https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html
     */

    /**
     * Signals that the given file path does not fulfill the storage filepath
     * constraints.
     */
    public static class InvalidStorageFilePathException extends IllegalValueException {
        public InvalidStorageFilePathException(String message) {
            super(message);
        }
    }

    /**
     * Signals that some error has occured while trying to convert and
     * read/write data between the application and the storage file.
     */
    public static class StorageOperationException extends Exception {
        public StorageOperationException(String message) {
            super(message);
        }
    }

    private final JAXBContext jaxbContext;

    public final Path path;

    /**
     * @throws InvalidStorageFilePathException
     *             if the default path is invalid
     */
    public StorageFile() throws InvalidStorageFilePathException {
        this(DEFAULT_STORAGE_FILEPATH);
    }

    /**
     * @throws InvalidStorageFilePathException
     *             if the given file path is invalid
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
     * Returns true if the given path is acceptable as a storage file. The file
     * path is considered acceptable if it ends with '.xml'
     */
    private static boolean isValidPath(Path filePath) {
        return filePath.toString().endsWith(".xml");
    }

    /**
     * Checks if the save file still exists
     *
     * @throws StorageOperationException
     *             if there were errors converting and/or storing data to file.
     */
    public void checkSaveFile(AddressBook addressBook) throws StorageOperationException {

        /*
         * Note: Note the 'try with resource' statement below. More info:
         * https://docs.oracle.com/javase/tutorial/essential/exceptions/
         * tryResourceClose.html
         */
        try (final Reader fileReader = new BufferedReader(new FileReader(path.toFile()))) {
        } catch (IOException fnfe) {
            printMessageForFileNotFound();
            this.writeToFile(addressBook);
        } 
        this.writeToFile(addressBook);
    }

    /**
     * Writes data to file, and creates a new save file if not present
     *
     * @throws StorageOperationException
     *             if there were errors converting and/or storing data to file.
     */
    private void writeToFile(AddressBook addressBook) throws StorageOperationException {
        try (final Writer fileWriter = new BufferedWriter(new FileWriter(path.toFile()))) {
            final AdaptedAddressBook toSave = new AdaptedAddressBook(addressBook);
            final Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(toSave, fileWriter);
        }  catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (JAXBException jaxbe) {
            throw new StorageOperationException("Error converting address book into storage format");
        }
    }

    /**
     * Loads data from this storage file.
     *
     * @throws StorageOperationException
     *             if there were errors reading and/or converting data from
     *             file.
     */
    public AddressBook load() throws StorageOperationException {
        try (final Reader fileReader = new BufferedReader(new FileReader(path.toFile()))) {

            final Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            final AdaptedAddressBook loaded = (AdaptedAddressBook) unmarshaller.unmarshal(fileReader);
            // manual check for missing elements
            if (loaded.isAnyRequiredFieldMissing()) {
                throw new StorageOperationException("File data missing some elements");
            }
            return loaded.toModelType();

            /*
             * Note: Here, we are using an exception to create the file if it is
             * missing. However, we should minimize using exceptions to
             * facilitate normal paths of execution. If we consider the missing
             * file as a 'normal' situation (i.e. not truly exceptional) we
             * should not use an exception to handle it.
             */

            // create empty file if not found
        } catch (FileNotFoundException fnfe) {
            final AddressBook empty = new AddressBook();
            checkSaveFile(empty);
            return empty;

            // other errors
        } catch (IOException ioe) {
            throw new StorageOperationException("Error writing to file: " + path);
        } catch (JAXBException jaxbe) {
            throw new StorageOperationException("Error parsing file data format");
        } catch (IllegalValueException ive) {
            throw new StorageOperationException("File contains illegal data values; data type constraints not met");
        }
    }

    /**
     * Prints a message for the user such that he knows the save file was not
     * found
     */
    private static void printMessageForFileNotFound() {
        System.out.println(MESSAGE_FILE_NOT_FOUND);
    }

    public String getPath() {
        return path.toString();
    }

}
