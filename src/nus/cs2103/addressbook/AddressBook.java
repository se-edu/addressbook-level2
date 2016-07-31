package nus.cs2103.addressbook;
/* ==============NOTE TO STUDENTS======================================
 * This class is not written in pure Object-Oriented fashion. 
 * Yes, it is possible to write non-OO code using an OO language.
 * ====================================================================
 */

import java.io.*;
import java.util.*;

/**
 * This class is used to store, remove and retrieve person data which are saved
 * in a text file. Each person has a name, a phone number (p/) and an email (e/).
 *
 * Furthermore, you can search for persons by name using keywords.
 * When multiple items are displayed, they are ordered by insertion time.
 *
 * Further command information can be found in the README or by entering 'help' as input to the program.
 * The program takes 1 argument at the start: the path of the storage file.
 * If no arguments are found, it uses a default storage file path.
 **/
public class AddressBook {

    /*
     * ==============NOTE TO STUDENTS======================================
     * These messages shown to the user are defined in one place for convenient
     * editing and proof reading. Such messages are considered part of the UI
     * and may be subjected to review by UI experts or technical writers. Note
     * that Some of the strings below include '%1$s' etc to mark the locations
     * at which java String.format(...) method can insert values.
     * ====================================================================
     */
    public static final String MESSAGE_ADDED = "New person added: %1$s, Phone: %2$s, Email: %3$s";
    public static final String MESSAGE_ADDRESSBOOK_CLEARED = "Address book has been cleared!";
    public static final String MESSAGE_COMMAND_HELP = "%1$s: %2$s";
    public static final String MESSAGE_COMMAND_HELP_PARAMETERS = "\tParameters: %1$s";
    public static final String MESSAGE_COMMAND_HELP_EXAMPLE = "\tExample: %1$s";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    public static final String MESSAGE_DISPLAY_PERSON_DATA = "%1$s  Phone Number: %2$s  Email: %3$s";
    public static final String MESSAGE_DISPLAY_LIST_ELEMENT_INDEX = "%1$d. ";
    public static final String MESSAGE_EXITING = "Exiting Address Book...";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format: %1$s \n%2$s";
    public static final String MESSAGE_INVALID_PROGRAM_ARGS = "Correct program argument format:"
                                                            + "\n\tjava AddressBook"
                                                            + "\n\tjava AddressBook [custom storage file path]";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";
    public static final String MESSAGE_INVALID_PERSON_LINE_IN_FILE = "Storage file contents invalid:"
                                                                   + "\n\tFile: %1$s\tLine %2$d:"
                                                                   + "\n\tNot a valid person:\t%3$s";
    public static final String MESSAGE_PERSON_NOT_IN_ADDRESSBOOK = "Person could not be found in address book";
    public static final String MESSAGE_ERROR_CREATING_STORAGE_FILE = "Error: unable to create file: %1$s";
    public static final String MESSAGE_ERROR_MISSING_STORAGE_FILE = "Storage file missing: %1$s";
    public static final String MESSAGE_ERROR_READING_FROM_FILE = "Unexpected error: unable to read from file: %1$s";
    public static final String MESSAGE_ERROR_WRITING_TO_FILE = "Unexpected error: unable to write to file: %1$s";
    public static final String MESSAGE_PERSONS_FOUND_OVERVIEW = "%1$d persons found!";
    public static final String MESSAGE_STORAGE_FILE_CREATED = "Created new empty storage file: %1$s";
    public static final String MESSAGE_WELCOME = "Welcome to your Address Book!";

    // These are the prefix strings to define the data type of a command parameter
    public static final String PERSON_DATA_PREFIX_PHONE = "p/";
    public static final String PERSON_DATA_PREFIX_EMAIL = "e/";

    public static final String PERSON_STRING_REPRESENTATION = "%1$s " // name
                                                            + PERSON_DATA_PREFIX_PHONE + "%2$s " // phone
                                                            + PERSON_DATA_PREFIX_EMAIL + "%3$s"; // email

    public static final String COMMAND_WORD_ADD = "add";
    public static final String COMMAND_ADD_DESC = "Adds a person to the address book.";
    public static final String COMMAND_ADD_PARAMETERS = "[name] "
                                                      + PERSON_DATA_PREFIX_PHONE + "[phone number] "
                                                      + PERSON_DATA_PREFIX_EMAIL + "[email]";
    public static final String COMMAND_ADD_EXAMPLE = COMMAND_WORD_ADD + " John Doe p/98765432 e/johnd@gmail.com";

    public static final String COMMAND_WORD_FIND = "find";
    public static final String COMMAND_FIND_DESC = "Retrieve persons with names containing the keyword.";
    public static final String COMMAND_FIND_PARAMETERS = "[keyword 1], [keyword 2], ...";
    public static final String COMMAND_FIND_EXAMPLE = COMMAND_WORD_FIND + " alice bob charlie";

    public static final String COMMAND_WORD_LIST = "list";
    public static final String COMMAND_LIST_DESC = "Lists all persons in added order.";
    public static final String COMMAND_LIST_EXAMPLE = COMMAND_WORD_LIST;

    public static final String COMMAND_WORD_DELETE = "delete";
    public static final String COMMAND_DELETE_DESC = "Delete the person identified by the given index number.";
    public static final String COMMAND_DELETE_PARAMETER = "[target index number]";
    public static final String COMMAND_DELETE_EXAMPLE = COMMAND_WORD_DELETE + " 1";

    public static final String COMMAND_WORD_CLEAR = "clear";
    public static final String COMMAND_CLEAR_DESC = "Clears address book permanently.";
    public static final String COMMAND_CLEAR_EXAMPLE = COMMAND_WORD_CLEAR;

    public static final String COMMAND_WORD_HELP = "help";
    public static final String COMMAND_HELP_DESC = "Shows program usage instructions.";
    public static final String COMMAND_HELP_EXAMPLE = COMMAND_WORD_HELP;

    public static final String COMMAND_WORD_EXIT = "exit";
    public static final String COMMAND_EXIT_DESC = "Exit the program.";
    public static final String COMMAND_EXIT_EXAMPLE = COMMAND_WORD_EXIT;


    // These are ordering indexes for the different data parameters of a person.
    // Used by the internal person String[] storage format
    public static final int PERSON_DATA_INDEX_NAME = 0;
    public static final int PERSON_DATA_INDEX_PHONE = 1;
    public static final int PERSON_DATA_INDEX_EMAIL = 2;
    public static final int PERSON_DATA_COUNT = 3; // 3 types of internal data for a person

    // Listings displayed to the user use 1-indexing, but the internal model uses 0-indexing
    public static final int DISPLAYED_INDEX_OFFSET = 1;

    // This filename is used if the user doesn't provide the file name
	public static final String DEFAULT_STORAGE_FILEPATH = "storage.txt";
    // If the first non-whitespace character in a user's input line is this, that line will be ignored.
    public static final char INPUT_COMMENT_MARKER = '#';

    /*
     * This variable is declared for the whole class (instead of declaring it
     * inside the readUserCommand() method to facilitate automated testing using
     * the I/O redirection technique. If not, only the first line of the input
     * text file will be processed. TODO FIND OUT WHY
     */
    private static final Scanner SCANNER = new Scanner(System.in);

    // Model tracking all persons in the address book
    private static final List<String[]> ALL_PERSONS = new ArrayList<>();

    /**
     * Stores the last viewed person listing (from commands like find and list)
     * Commands using an index to target a person will refer to the indices in this list.
     *
     * Persons in this list are the same objects as the corresponding elements in the backing model.
     * Deleting persons in the backing model may not necessarily update this latest view snapshot.
     */
    private static List<String[]> latestPersonListingView = getAllPersonsInAddressBook(); // initial view is of all
    private static String latestUserInput;

    private static String storageFilePath;

    private static boolean isExitRequested = false;

    /*
     * ==============NOTE TO STUDENTS======================================
     * Notice how this method solves the whole problem at a very high level. We
     * can understand the high-level logic of the program by reading this method
     * alone.
     * ====================================================================
     */
    public static void main(String[] args) {
        processProgramArgs(args);
        showToUser(MESSAGE_WELCOME);
        loadAddressBookModelFromStorage();
        while (!isExitRequested) {
            latestUserInput = getUserInput();
            String feedback = parseAndExecuteCommand(latestUserInput);
            showToUser(feedback);
        }
        cleanup();
    }

    /*
     * ==============NOTE TO STUDENTS==========================================
     * If the reader wants a deeper understanding of the solution, he/she can go
     * to the next level of abstraction by reading the methods (given below)
     * that is referenced by the method above.
     * ====================================================================
     */

    /**
     * Processes the program main method run arguments.
     * Also handles parsing and validating the arguments.
     * If any arguments are invalid, the corresponding default configuration is used.
     * 
     * @param args full program run arguments passed to application main method
     */
    private static void processProgramArgs(String[] args) {
        informUserIfProgramArgsAreInvalid(args);
        storageFilePath = args.length == 1 ? args[0] : DEFAULT_STORAGE_FILEPATH;
    }

    /**
     * Informs user if program args are invalid.
     *
     * @param args full program run arguments passed to application main method
     */
    private static void informUserIfProgramArgsAreInvalid(String[] args) {
        if (args.length > 2) {
            showToUser(MESSAGE_INVALID_PROGRAM_ARGS);
        }
    }

    /**
     * Initialises the model using the storage file. If storage file does not exist, tries to create it first.
     */
    private static void loadAddressBookModelFromStorage() {
        tryToCreateFileIfMissing(storageFilePath);
        if (!new File(storageFilePath).exists()) { // make sure storage file exists
            indicateProgramShouldExit();
            return;
        }

        final Optional<List<String[]>> successfullyLoadedPersons = loadPersonsFromFile(storageFilePath);
        if (!successfullyLoadedPersons.isPresent()) { // make sure storage file decoded successfully
            indicateProgramShouldExit();
            return;
        }
        initialiseAddressBookModel(successfullyLoadedPersons.get());
    }

    /**
     * Cleans up all loose ends, and prepares program for termination.
     */
    private static void cleanup() {
        // any checks, finalisations, cleanup logic, resource closing here.
        // very useful when multithreading comes into play.
    }

    /*
     * ===========================================
     *           COMMAND LOGIC
     * ===========================================
     */

    /**
     * Checks which command the user want to trigger, then run the corresponding
     * function
     * 
     * @param userInputString  raw input from user
     * @return  Message from respective function
     */
    public static String parseAndExecuteCommand(String userInputString) {
        final String[] commandTypeAndParams = splitCommandWordAndArgs(userInputString);
        final String commandType = commandTypeAndParams[0];
        final String commandArgs = commandTypeAndParams[1];
        switch (commandType) {
        case COMMAND_WORD_ADD:
            return executeAddPerson(commandArgs);
        case COMMAND_WORD_FIND:
            return executeFindPersons(commandArgs);
        case COMMAND_WORD_LIST:
            return executeListAllPersonsInAddressBook();
        case COMMAND_WORD_DELETE:
            return executeDeletePerson(commandArgs);
        case COMMAND_WORD_CLEAR:
            return executeClearAddressBook();
        case COMMAND_WORD_HELP:
            return getUsageInfoForAllCommands();
        case COMMAND_WORD_EXIT:
            return executeExitProgramRequest();
        default:
            return getMessageForInvalidCommandInput(getUsageInfoForAllCommands());
        }
    }

    /**
     * Splits raw user input into command word and command arguments string
     *
     * @return  size 2 array; first element is the command type and second element is the arguments string
     */
    private static String[] splitCommandWordAndArgs(String rawUserInput) {
        final String[] split =  rawUserInput.trim().split("\\s+", 2);
        return split.length == 2 ? split : new String[] { split[0] , "" }; // else case: no parameters
    }

    /**
     * Constructs a generic feedback message for an invalid command from user, with instructions for correct usage.
     *
     * @param correctUsageInfo message showing the correct usage
     * @return invalid command args feedback message
     */
    private static String getMessageForInvalidCommandInput(String correctUsageInfo) {
        return String.format(MESSAGE_INVALID_COMMAND_FORMAT, latestUserInput, correctUsageInfo);
    }

    /**
     * Adds a person (specified by the command args) to the address book.
     * The entire command arguments string is treated as a string representation of the person to add.
     *
     * @param commandArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private static String executeAddPerson(String commandArgs) {
        // try decoding a person from the raw args
        final Optional<String[]> successfullyDecodedPerson = decodePersonFromStringRepresentation(commandArgs);

        // checks if args are valid (cannot decode if invalid)
        if (!successfullyDecodedPerson.isPresent()) {
            return getMessageForInvalidCommandInput(getUsageInfoForAddCommand());
        }

        // add the person as specified
        final String[] toAdd = successfullyDecodedPerson.get();
        addPersonToAddressBook(toAdd);
        return getMessageForSuccessfulAddPerson(toAdd);
    }

    /**
     * Constructs a feedback message for a successful add person command execution.
     *
     * @see #executeAddPerson(String)
     * @param addedPerson person who was successfully added
     * @return successful add person feedback message
     */
    private static String getMessageForSuccessfulAddPerson(String[] addedPerson) {
        return String.format(MESSAGE_ADDED,
                getNameFromPerson(addedPerson), getPhoneFromPerson(addedPerson), getEmailFromPerson(addedPerson));
    }

    /**
     * Finds and lists all persons in address book whose name contains any of the argument keywords.
     * 
     * @param commandArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private static String executeFindPersons(String commandArgs) {
        final Set<String> keywords = extractKeywordsFromFindPersonArgs(commandArgs);
        final List<String[]> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        showToUser(personsFound);
        return getMessageForPersonsDisplayedSummary(personsFound);
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    private static String getMessageForPersonsDisplayedSummary(List<String[]> personsDisplayed) {
        return String.format(MESSAGE_PERSONS_FOUND_OVERVIEW, personsDisplayed.size());
    }

    /**
     * Extract keywords from the command arguments given for the find persons command.
     *
     * @param findPersonCommandArgs full command args string for the find persons command
     * @return set of keywords as specified by args
     */
    private static Set<String> extractKeywordsFromFindPersonArgs(String findPersonCommandArgs) {
        return new HashSet<>(splitByWhitespace(findPersonCommandArgs.trim()));
    }

    /**
     * Retrieve all persons in the full model whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons in full model with name containing some of the keywords
     */
    private static List<String[]> getPersonsWithNameContainingAnyKeyword(Collection<String> keywords) {
        final List<String[]> matchedPersons = new ArrayList<>();
        for (String[] person : getAllPersonsInAddressBook()) {
            final Set<String> wordsInName = new HashSet<>(splitByWhitespace(getNameFromPerson(person)));
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

    /**
     * Deletes person identified using last displayed index.
     * 
     * @param commandArgs full command args string from the user
     * @return feedback display message for the operation result
     */
    private static String executeDeletePerson(String commandArgs) {
        if (!isDeletePersonArgsValid(commandArgs)) {
            return getMessageForInvalidCommandInput(getUsageInfoForDeleteCommand());
        }
        final int targetVisibleIndex = extractTargetIndexFromDeletePersonArgs(commandArgs);
        if (!isDisplayIndexValidForLastPersonListingView(targetVisibleIndex)) {
            return MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;
        }
        final String[] targetInModel = getPersonByLastVisibleIndex(targetVisibleIndex);
        return deletePersonFromAddressBook(targetInModel) ? getMessageForSuccessfulDelete(targetInModel) // success
                                                          : MESSAGE_PERSON_NOT_IN_ADDRESSBOOK; // not found
    }

    /**
     * Checks validity of delete person argument string's format.
     *
     * @param rawArgs raw command args string for the delete person command
     * @return whether the input args string is valid
     */
    private static boolean isDeletePersonArgsValid(String rawArgs) {
        try {
            final int extractedIndex = Integer.parseInt(rawArgs.trim()); // use standard libraries to parse
            return extractedIndex >= DISPLAYED_INDEX_OFFSET;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    /**
     * Extracts the target's index from the raw delete person args string
     *
     * @param rawArgs raw command args string for the delete person command
     * @return extracted index
     */
    private static int extractTargetIndexFromDeletePersonArgs(String rawArgs) {
        return Integer.parseInt(rawArgs.trim());
    }

    /**
     * Checks that the given index is within bounds and valid for the last shown person list view.
     *
     * @param index to check
     * @return whether it is valid
     */
    private static boolean isDisplayIndexValidForLastPersonListingView(int index) {
        return index >= DISPLAYED_INDEX_OFFSET && index < getLatestPersonListingView().size() + DISPLAYED_INDEX_OFFSET;
    }

    /**
     * Constructs a feedback message for a successful delete person command execution.
     *
     * @see #executeDeletePerson(String)
     * @param deletedPerson successfully deleted
     * @return successful delete person feedback message
     */
    private static String getMessageForSuccessfulDelete(String[] deletedPerson) {
        return String.format(MESSAGE_DELETE_PERSON_SUCCESS, getMessageForFormattedPersonData(deletedPerson));
    }

    /**
     * Clears all persons in the address book.
     * 
     * @return feedback display message for the operation result
     */
    private static String executeClearAddressBook() {
        clearAddressBook();
        return MESSAGE_ADDRESSBOOK_CLEARED;
    }

    /**
     * Displays all persons in the address book to the user; in added order.
     *
     * @return feedback display message for the operation result
     */
    private static String executeListAllPersonsInAddressBook() {
        List<String[]> toBeDisplayed = getAllPersonsInAddressBook();
        showToUser(toBeDisplayed);
        return getMessageForPersonsDisplayedSummary(toBeDisplayed);
    }

    /**
     * Request to terminate the program.
     *
     * @return feedback display message for the operation result
     */
    private static String executeExitProgramRequest() {
        indicateProgramShouldExit();
        return MESSAGE_EXITING;
    }

    /*
     * ===========================================
     *               UI LOGIC
     * ===========================================
     */

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores lines with first non-whitespace char equal to {@link #INPUT_COMMENT_MARKER} (considered comments)
     *
     * @return full line entered by the user
     */
    private static String getUserInput() {
        System.out.print("\nEnter command: ");
        String inputLine = SCANNER.nextLine();
        // silently consume all comment lines
        while (inputLine.trim().charAt(0) == INPUT_COMMENT_MARKER) {
            inputLine = SCANNER.nextLine();
        }
        return inputLine;
    }

    /**
     * Shows a message to the user
     *
     * @param message to show
     */
    private static void showToUser(String message) {
        System.out.println(message);
    }

    /**
     * Formats and shows an indexed list of persons to the user.
     *
     * @param persons to show
     */
    private static void showToUser(List<String[]> persons) {
        final StringBuilder messageAccumulator = new StringBuilder();
        for (int i = 0; i < persons.size(); i++) {
            final String[] person = persons.get(i);
            final int displayIndex = i + DISPLAYED_INDEX_OFFSET;
            messageAccumulator.append(getIndexedPersonListElementMessage(displayIndex, person))
                              .append('\n');
        }
        showToUser(messageAccumulator.toString());
        updateLatestViewedPersonListing(persons);
    }

    /**
     * Construct a prettified listing element message to represent a person and their data.
     *
     * @param visibleIndex visible index for this listing
     * @param person to show
     * @return formatted listing message with index
     */
    private static String getIndexedPersonListElementMessage(int visibleIndex, String[] person) {
        return String.format(MESSAGE_DISPLAY_LIST_ELEMENT_INDEX, visibleIndex) + getMessageForFormattedPersonData(person);
    }

    /**
     * Construct a prettified string to show the user a person's data.
     *
     * @param person to show
     * @return formatted message showing internal state
     */
    private static String getMessageForFormattedPersonData(String[] person) {
        return String.format(MESSAGE_DISPLAY_PERSON_DATA,
                getNameFromPerson(person), getPhoneFromPerson(person), getEmailFromPerson(person));
    }

    /**
     * Track the latest person listing view the user has seen.
     *
     * @param newListing the new listing of persons
     */
    private static void updateLatestViewedPersonListing(List<String[]> newListing) {
        // clone to insulate from future changes to arg list
        // lookup: defensive copying
        latestPersonListingView = new ArrayList<>(newListing);
    }

    /**
     * Retrieve the person identified by the displayed index from the last shown listing of persons.
     *
     * @param lastVisibleIndex displayed index from last shown person listing
     * @return the actual person object in the last shown person listing
     */
    private static String[] getPersonByLastVisibleIndex(int lastVisibleIndex) {
       return latestPersonListingView.get(lastVisibleIndex - DISPLAYED_INDEX_OFFSET);
    }

    /**
     * @return unmodifiable list view of the last person listing view
     */
    private static List<String[]> getLatestPersonListingView() {
        return Collections.unmodifiableList(latestPersonListingView);
    }


    /*
     * ===========================================
     *             STORAGE LOGIC
     * ===========================================
     */

    /**
     * Create storage file if it does not exist. Shows feedback to user.
     *
     * @param filePath file to create if not present
     */
    private static void tryToCreateFileIfMissing(String filePath) {
        final File storageFile = new File(filePath);
        try {
            if (storageFile.createNewFile()) {
                showToUser(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, filePath));
                showToUser(String.format(MESSAGE_STORAGE_FILE_CREATED, filePath));
            }
        } catch (IOException ioe) {
            showToUser(String.format(MESSAGE_ERROR_CREATING_STORAGE_FILE, filePath));
        }
    }

    /**
     * Converts contents of a file into a list of persons. Shows feedback to user.
     *
     * @param filePath file to load from
     * @return able to decode: optional containing list of persons in file
     *         problems decoding: empty optional
     */
    private static Optional<List<String[]>> loadPersonsFromFile(String filePath) {
        try (final BufferedReader storageReader =
                     new BufferedReader(new FileReader(filePath))) {
            final List<String[]> decodedPersons = new ArrayList<>();
            int lineNumber = 1;

            // do for each line in the file, also tracks line number
            for (String line = storageReader.readLine(); line != null; line = storageReader.readLine(), lineNumber++) {
                final Optional<String[]> successfullyDecodedPerson = decodePersonFromStringRepresentation(line);
                // unable to decode person means file content format invalid; stop program
                if (!successfullyDecodedPerson.isPresent()) {
                    showToUser(getMessageForInvalidPersonLineInFile(filePath, lineNumber, line));
                    return Optional.empty();
                }
                decodedPersons.add(successfullyDecodedPerson.get());
            }
            return Optional.of(decodedPersons); // success

        } catch (FileNotFoundException fnfe) {
            showToUser(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, filePath));
        } catch (IOException ioe) {
            showToUser(String.format(MESSAGE_ERROR_READING_FROM_FILE, filePath));
        }
        return Optional.empty(); // failure
    }

    /**
     * Constructs a feedback message to pinpoint the first invalid person line found in the storage file.
     *
     * @see #loadPersonsFromFile(String)
     * @param file problem file as string
     * @param lineNumber problem line number
     * @param lineContents contents of problem line
     * @return feedback message for invalid person line found in file
     */
    private static String getMessageForInvalidPersonLineInFile(String file, int lineNumber, String lineContents) {
        return String.format(MESSAGE_INVALID_PERSON_LINE_IN_FILE, file, lineNumber, lineContents);
    }

    /**
     * Saves
     * NOTE: will try to terminate program if there is an error saving to file.
     *
     * @param filePath file for saving
     */
    private static void savePersonsToFile(Collection<String[]> persons, String filePath) {
        try (final BufferedWriter storageWriter =
                     new BufferedWriter(new FileWriter(filePath, false))) {
            for (String[] person : persons) {
                storageWriter.write(encodePersonToStringRepresentation(person));
                storageWriter.newLine();
            }
            storageWriter.flush();
        } catch (FileNotFoundException fnfe) {
            showToUser(String.format(MESSAGE_ERROR_MISSING_STORAGE_FILE, filePath));
            indicateProgramShouldExit();
        } catch (IOException ioe) {
            showToUser(String.format(MESSAGE_ERROR_WRITING_TO_FILE, filePath));
            indicateProgramShouldExit();
        }
    }


    /*
     * ================================================================================
     *        INTERNAL ADDRESS BOOK MODEL METHODS (Changes to model must use these)
     * ================================================================================
     */

    /**
     * Adds a person to the address book. Saves changes to storage file.
     *
     * @param person to add
     */
    private static void addPersonToAddressBook(String[] person) {
        ALL_PERSONS.add(person);
        savePersonsToFile(getAllPersonsInAddressBook(), storageFilePath);
    }

    /**
     * Deletes a person from the address book, target is identified by it's absolute index in the full list.
     * Saves changes to storage file.
     *
     * @param index absolute index of person to delete (index within {@link #ALL_PERSONS})
     */
    private static void deletePersonFromAddressBook(int index) {
        ALL_PERSONS.remove(index);
        savePersonsToFile(getAllPersonsInAddressBook(), storageFilePath);
    }

    /**
     * Deletes the person in the argument from the addressbook if it is inside. Saves any changes to storage file.
     *
     * @param exactPerson the actual person inside the model (exactPerson == the person to delete in the full list)
     * @return true if the given person was found and deleted in the model
     */
    private static boolean deletePersonFromAddressBook(String[] exactPerson) {
        final boolean changed = ALL_PERSONS.remove(exactPerson);
        if (changed) {
            savePersonsToFile(getAllPersonsInAddressBook(), storageFilePath);
        }
        return changed;
    }

    /**
     * @return unmodifiable list view of all persons in the address book
     */
    private static List<String[]> getAllPersonsInAddressBook() {
        return Collections.unmodifiableList(ALL_PERSONS);
    }

    /**
     * Clears all persons in the address book and saves changes to file.
     */
    private static void clearAddressBook() {
        ALL_PERSONS.clear();
        savePersonsToFile(getAllPersonsInAddressBook(), storageFilePath);
    }

    /**
     * Resets the internal model with the given data. Does not save to file.
     *
     * @param persons list of persons to initialise the model with
     */
    private static void initialiseAddressBookModel(List<String[]> persons) {
        ALL_PERSONS.clear();
        ALL_PERSONS.addAll(persons);
    }

    /*
     * ===========================================
     *         SINGLE PERSON METHODS
     * ===========================================
     */

    /**
     * @param person whose name you want
     * @return person's name
     */
    private static String getNameFromPerson(String[] person) {
        return person[PERSON_DATA_INDEX_NAME];
    }

    /**
     * @param person whose phone number you want
     * @return person's phone number
     */
    private static String getPhoneFromPerson(String[] person) {
        return person[PERSON_DATA_INDEX_PHONE];
    }

    /**
     * @param person whose email you want
     * @return person's email
     */
    private static String getEmailFromPerson(String[] person) {
        return person[PERSON_DATA_INDEX_EMAIL];
    }

    /**
     * Create a person for use in the internal model.
     *
     * @param name of person
     * @param phone without data prefix
     * @param email without data prefix
     * @return constructed person
     */
    private static String[] makePersonFromData(String name, String phone, String email) {
        final String[] person = new String[PERSON_DATA_COUNT];
        person[PERSON_DATA_INDEX_NAME] = name;
        person[PERSON_DATA_INDEX_PHONE] = phone;
        person[PERSON_DATA_INDEX_EMAIL] = email;
        return person;
    }

    /**
     * Encodes a person into a decodable and readable string representation.
     *
     * @param person to be encoded
     * @return encoded string
     */
    private static String encodePersonToStringRepresentation(String[] person) {
        return String.format(PERSON_STRING_REPRESENTATION,
                getNameFromPerson(person), getPhoneFromPerson(person), getEmailFromPerson(person));
    }

    /**
     * Validates and decodes a person from it's supposed string representation.
     *
     * @param encoded string to be decoded
     * @return able to decode: optional containing decoded person
     *         unable to decode: empty optional
     */
    private static Optional<String[]> decodePersonFromStringRepresentation(String encoded) {
        // check that we can extract the parts of a person from the encoded string
        if (!isPersonDataExtractableFrom(encoded)) {
            return Optional.empty();
        }
        final String[] decodedPerson = makePersonFromData(
                extractNameFromPersonString(encoded),
                extractPhoneFromPersonString(encoded),
                extractEmailFromPersonString(encoded)
        );
        // check that the constructed person is valid
        return isPersonDataValid(decodedPerson) ? Optional.of(decodedPerson) : Optional.empty();
    }

    /**
     * Checks whether person data (email, name, phone etc) can be extracted from the argument string.
     * Format is [name] p/[phone] e/[email], phone and email positions can be swapped.
     *
     * @param personData person string representation
     * @return whether format of add command arguments allows parsing into individual arguments
     */
    private static boolean isPersonDataExtractableFrom(String personData) {
        final String matchAnyPersonDataPrefix = PERSON_DATA_PREFIX_PHONE + '|' + PERSON_DATA_PREFIX_EMAIL;
        final String[] splitArgs = personData.trim().split(matchAnyPersonDataPrefix);
        return splitArgs.length == 3 // 3 arguments
                && !splitArgs[0].isEmpty() // non-empty arguments
                && !splitArgs[1].isEmpty()
                && !splitArgs[2].isEmpty();
    }

    /**
     * Extracts substring representing person name from person string representation
     *
     * @param encoded person string representation
     * @return name argument
     */
    private static String extractNameFromPersonString(String encoded) {
        final int indexOfPhonePrefix = encoded.indexOf(PERSON_DATA_PREFIX_PHONE);
        final int indexOfEmailPrefix = encoded.indexOf(PERSON_DATA_PREFIX_EMAIL);
        // name is leading substring up to first data prefix symbol
        return encoded.substring(0, Math.min(indexOfEmailPrefix, indexOfPhonePrefix)).trim();
    }

    /**
     * Extracts substring representing phone number from person string representation
     *
     * @param encoded person string representation
     * @return phone number argument WITHOUT prefix
     */
    private static String extractPhoneFromPersonString(String encoded) {
        final int indexOfPhonePrefix = encoded.indexOf(PERSON_DATA_PREFIX_PHONE);
        final int indexOfEmailPrefix = encoded.indexOf(PERSON_DATA_PREFIX_EMAIL);
        return indexOfPhonePrefix > indexOfEmailPrefix
                // phone is last arg, target is from prefix to end of string
                ? removePrefixSign(
                encoded.substring(indexOfPhonePrefix, encoded.length()).trim(),
                PERSON_DATA_PREFIX_PHONE)
                // phone is middle arg, target is from own prefix to next prefix
                : removePrefixSign(
                encoded.substring(indexOfPhonePrefix, indexOfEmailPrefix).trim(),
                PERSON_DATA_PREFIX_PHONE);
    }

    /**
     * Extracts substring representing email from person string representation
     *
     * @param encoded person string representation
     * @return email argument WITHOUT prefix
     */
    private static String extractEmailFromPersonString(String encoded) {
        final int indexOfPhonePrefix = encoded.indexOf(PERSON_DATA_PREFIX_PHONE);
        final int indexOfEmailPrefix = encoded.indexOf(PERSON_DATA_PREFIX_EMAIL);
        return indexOfEmailPrefix > indexOfPhonePrefix
                // email is last arg, target is from prefix to end of string
                ? removePrefixSign(
                encoded.substring(indexOfEmailPrefix, encoded.length()).trim(),
                PERSON_DATA_PREFIX_EMAIL)
                // email is middle arg, target is from own prefix to next prefix
                : removePrefixSign(
                encoded.substring(indexOfEmailPrefix, indexOfPhonePrefix).trim(),
                PERSON_DATA_PREFIX_EMAIL);
    }

    /**
     * Validates a person's data fields
     *
     * @param person String array representing the person (used in internal model)
     * @return whether the given person has valid data
     */
    private static boolean isPersonDataValid(String[] person) {
        return isPersonDataValid(
                person[PERSON_DATA_INDEX_NAME], person[PERSON_DATA_INDEX_PHONE], person[PERSON_DATA_INDEX_EMAIL]);
    }

    /**
     * Validates individual data strings as legal person data fields
     *
     * @param name string to be checked as a person name
     * @param phone string to be checked as a phone number (WITHOUT PREFIX)
     * @param email string to be checked as an email (WITHOUT PREFIX)
     * @return whether arguments are valid for their corresponding data field types
     */
    private static boolean isPersonDataValid(String name, String phone, String email) {
        return isPersonNameValid(name) && isPersonPhoneValid(phone) && isPersonEmailValid(email);
    }

    /**
     * Validates string as a legal person name
     *
     * @param name to be validated
     * @return whether arg is a valid person name
     */
    private static boolean isPersonNameValid(String name) {
        return name.matches("(\\w|\\s)+");  // name is nonempty mixture of alphabets and whitespace
    }

    /**
     * Validates string as a legal person phone number
     *
     * @param phone to be validated
     * @return whether arg is a valid person phone number
     */
    private static boolean isPersonPhoneValid(String phone) {
        return phone.matches("\\d+");    // phone nonempty sequence of digits
    }

    /**
     * Validates string as a legal person email
     *
     * @param email to be validated
     * @return whether arg is a valid person email
     */
    private static boolean isPersonEmailValid(String email) {
        return email.matches("\\S+@\\S+\\.\\S+"); // email is [non-whitespace]@[non-whitespace].[non-whitespace]
    }


    /*
     * ===============================================
     *         COMMAND HELP INFO FOR USERS
     * ===============================================
     */

    /**
     * @return  Usage info for all commands
     */
    private static String getUsageInfoForAllCommands() {
        return getUsageInfoForAddCommand() + getUsageInfoForFindCommand() + getUsageInfoForViewCommand()
                + getUsageInfoForDeleteCommand() + getUsageInfoForClearCommand() + getUsageInfoForExitCommand()
                + getUsageInfoForHelpCommand();
    }

    /**
     * Builds string for showing 'add' command usage instruction
     * 
     * @return  'add' command usage instruction
     */
    private static String getUsageInfoForAddCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_ADD, COMMAND_ADD_DESC) + '\n'
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_ADD_PARAMETERS) + '\n'
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_ADD_EXAMPLE) + '\n';
    }

    /**
     * Builds string for showing 'find' command usage instruction
     *
     * @return  'find' command usage instruction
     */
    private static String getUsageInfoForFindCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_FIND, COMMAND_FIND_DESC) + '\n'
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_FIND_PARAMETERS) + '\n'
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_FIND_EXAMPLE) + '\n';
    }

    /**
     * Builds string for showing 'delete' command usage instruction
     *
     * @return  'delete' command usage instruction
     */
    private static String getUsageInfoForDeleteCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_DELETE, COMMAND_DELETE_DESC) + '\n'
                + String.format(MESSAGE_COMMAND_HELP_PARAMETERS, COMMAND_DELETE_PARAMETER) + '\n'
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_DELETE_EXAMPLE) + '\n';
    }

    /**
     * Builds string for showing 'clear' command usage instruction
     *
     * @return  'clear' command usage instruction
     */
    private static String getUsageInfoForClearCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_CLEAR, COMMAND_CLEAR_DESC) + '\n'
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_CLEAR_EXAMPLE) + '\n';
    }

    /**
     * Builds string for showing 'view' command usage instruction
     *
     * @return  'view' command usage instruction
     */
    private static String getUsageInfoForViewCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_LIST, COMMAND_LIST_DESC) + '\n'
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_LIST_EXAMPLE) + '\n';
    }

    /**
     * Builds string for showing 'help' command usage instruction
     *
     * @return  'help' command usage instruction
     */
    private static String getUsageInfoForHelpCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_HELP, COMMAND_HELP_DESC)
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_HELP_EXAMPLE);
    }

    /**
     * Builds string for showing 'exit' command usage instruction
     *
     * @return  'exit' command usage instruction
     */
    private static String getUsageInfoForExitCommand() {
        return String.format(MESSAGE_COMMAND_HELP, COMMAND_WORD_EXIT, COMMAND_EXIT_DESC)
                + String.format(MESSAGE_COMMAND_HELP_EXAMPLE, COMMAND_EXIT_EXAMPLE);
    }


    /*
     * ============================
     *         UTILITY
     * ============================
     */

    /**
     * Removes sign(p/, d/, etc) from parameter string
     * 
     * @param s  Parameter as a string
     * @param sign  Parameter sign to be removed
     * 
     * @return  Priority string without p/
     */
    private static String removePrefixSign(String s, String sign) {
        return s.replace(sign, "");
    }

    /**
     * Splits a source string into the list of substrings that were separated by whitespace.
     *
     * @param toSplit source string
     * @return split by whitespace
     */
    private static List<String> splitByWhitespace(String toSplit) {
        return Arrays.asList(toSplit.trim().split("\\s+"));
    }

    /**
     * Program will clean up and exit as soon as possible.
     */
    private static void indicateProgramShouldExit() {
        isExitRequested = true;
    }

}