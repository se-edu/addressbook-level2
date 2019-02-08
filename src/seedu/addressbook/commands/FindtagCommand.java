package seedu.addressbook.commands;

import java.util.*;

import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.tag.Tag;

/**
 * Finds and lists all persons in address book who contain the keyword as one of his/her tags.
 * Keyword matching is case sensitive.
 */

public class FindtagCommand extends Command {

    public static final String COMMAND_WORD = "findtag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose tags contain "
            + "the specified tag (case-sensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD ...\n"
            + "Example: " + COMMAND_WORD + " family";

    private final String keyword;

    public FindtagCommand (String keyword){
        this.keyword = keyword;
    }

    public String getKeyword(){
        return this.keyword;
    }

    @Override
    public CommandResult execute(){
        final List<ReadOnlyPerson> personsFound = getPersonsWithKeywordAsTag(keyword);
        return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
    }

    private List<ReadOnlyPerson> getPersonsWithKeywordAsTag(String keyword){
        final List<ReadOnlyPerson> matchedPerson = new ArrayList<>();
        for (ReadOnlyPerson person : addressBook.getAllPersons()){
            final Set<Tag> tags = person.getTags();
            Iterator<Tag> tagIterator = tags.iterator();
            boolean isKeywordContained = false;
            while (tagIterator.hasNext()){
                Tag currentTag = tagIterator.next();
                if (currentTag.tagName.equals(keyword)){
                    isKeywordContained = true;
                    break;
                }
            }
            if (isKeywordContained){
                matchedPerson.add(person);
            }
        }
        return matchedPerson;
    }
}
