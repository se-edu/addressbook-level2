package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.UniqueTagList;

/**
 * Finds and lists all persons in address book whose name contains any of the argument keywords.
 * Keyword matching is case sensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names or tags contain any of "
            + "the specified keywords (NOT case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " alice Bob AUNT charlie friend";

    private final Set<String> keywords;

    public FindCommand(Set<String> keywords) {
        this.keywords = keywords;
    }

    /**
     * Returns a copy of keywords in this command.
     */
    public Set<String> getKeywords() {
        return new HashSet<>(keywords);
    }

    @Override
    public CommandResult execute() {
        final List<ReadOnlyPerson> personsFound = getPersonsWithNameContainingAnyKeyword(keywords);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    /**
     * Retrieves all persons in the address book whose names contain some of the specified keywords.
     *
     * @param keywords for searching
     * @return list of persons found
     */
    private List<ReadOnlyPerson> getPersonsWithNameContainingAnyKeyword(Set<String> keywords) {
        final List<ReadOnlyPerson> matchedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()) {

            Set<String> wordsInName = new HashSet<>(person.getName().getWordsInName());

            String[] words=person.getOnlyTags().split(" ");

            Set<String> tagsForName= new HashSet<String>();

            Collections.addAll(tagsForName,words);

            keywords=changeSetElements(keywords);
            wordsInName=changeSetElements(wordsInName);
            tagsForName=changeSetElements(tagsForName);

            // additional tags
            String tagList=person.getOnlyTags();

            if ((!Collections.disjoint(wordsInName, keywords))||(!Collections.disjoint(tagsForName, keywords))) {
                matchedPersons.add(person);
            }
        }
        return matchedPersons;
    }



    // Method to convert all words to lowercase so that the comparison can be case-insensitive
    public Set<String> changeSetElements(Set<String> s)
    {
        Set<String> temp= new HashSet<String>();
        for(String i : s)
            temp.add(i.toLowerCase());
        s.clear();
        s.addAll(temp);
        return s;

    }

}
