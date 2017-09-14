package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.*;

/**
 * Finds and lists all persons in address book whose name or address contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindciCommand extends Command {

    public static final String COMMAND_WORD = "findci";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose name or address contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice BOB cHaRLie Avenue";

    private final Set<String> keywords;
    public FindciCommand(Set<String> keywords) {
        this.keywords = keywords;

    }
    /**
     * Returns a set of words made lowercase
     */
    private Set<String> lower(Set<String> words){
        Iterator<String> wordsIterator = words.iterator();
        Set<String> toReturn = new HashSet<String>();
        while(wordsIterator.hasNext()) {
            String upper = wordsIterator.next();
            String lower = upper.toLowerCase();
            toReturn.add(lower);
        }
        return toReturn;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeywordci(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeywordci(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {
            final Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());
            final Set<String> wordsInAddress = new HashSet<>(person.getAddress().getWordsInAddress());
            if ((!Collections.disjoint(lower(wordsInName), lower(keywords))) || (!Collections.disjoint(lower(wordsInAddress), lower(keywords)))) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }

}
