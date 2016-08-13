package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.addressbook.TextUi.*;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindPersonsByWordsInNameCommand implements Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contain any of "
            + "the specified keywords (case-sensitive) and displays them as a list with index numbers."
            + LS + "Parameters: KEYWORD [MORE_KEYWORDS]..."
            + LS + "Example: " + COMMAND_WORD + " alice bob charlie";

    public static final Pattern ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    private AddressBook addressBook;
    private TextUi ui;
    private final String args;

    /**
     * @param args full command args string from the user
     * @param addressBook to search in
     * @param ui for displaying the list of found persons
     */
    public FindPersonsByWordsInNameCommand(String args, AddressBook addressBook, TextUi ui) {
        this.addressBook = addressBook;
        this.args = args;
        this.ui = ui;
    }

    @Override
    public void injectDependencies(TextUi ui, AddressBook addressBook) {
        this.addressBook = addressBook;
        this.ui = ui;
    }

    @Override
    public String execute() {
        if (!isValidArgs(args)) {
            return String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);
        }
        final Set<String> keywords = extractKeywordsFromArgs();
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        ui.showPersonListView(personsFound);
        return getMessageForPersonListShownSummary(personsFound);
    }

    public static boolean isValidArgs(String findArgs) {
        return findArgs.trim().matches(ARGS_FORMAT.pattern());
    }

    /**
     * Extracts all keywords for the find command from the given arguments.
     */
    private Set<String> extractKeywordsFromArgs() {
        final Matcher matcher = ARGS_FORMAT.matcher(args.trim());
        matcher.matches();
        final Collection<String> keywords = Arrays.asList(matcher.group("keywords").split("\\s+"));
        return new HashSet<>(keywords);
    }

    /**
     * Retrieve all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            if (!Collections.disjoint(wordsInName, keywords)) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
