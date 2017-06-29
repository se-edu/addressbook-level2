package seedu.addressbook.commands;

import java.util.ArrayList;
import java.util.List;
import java.lang.StringBuilder;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.tag.UniqueTagList;
import seedu.addressbook.data.tag.Tag;

/**
 * Associates between Person and Tag, keeping a history of all Tags added/deleted.
 */
public class Tagging {

    public static final boolean IS_ADD = true;

    private final Person person;
    private final Tag tagChanged;
    private final boolean isAdd;

    private boolean isSuccessful;

    private static final List<Tagging> taggings = new ArrayList<Tagging>();
    
    /**
     * Constructor ensures that all fields are initialised
     */
    public Tagging(Person person, Tag tagChanged, boolean isAdd) {
        this.person = person;
        this.tagChanged = tagChanged;
        this.isAdd = isAdd;
        executePersonTagModification();
        if (isSuccessful) {
            taggings.add(this);
        }
    }

    private void executePersonTagModification() {
        final UniqueTagList oldTagList = person.getTags();
        if (isAdd == IS_ADD) {
            try {
                oldTagList.addAll(new UniqueTagList(tagChanged));
                isSuccessful = true;
            } catch (DuplicateTagException exception) {
                isSuccessful = false;
            }
        } else { // to remove
            if (oldTagList.remove(tagChanged)) { // success
                isSuccessful = true;
            } else { // failure
                isSuccessful = false;
            }
        }
        if (isSuccessful) {
            person.setTags(oldTagList);
        }
    }

    public static List<Tagging> getTaggingsList() {
        return taggings;
    }

    /**
     * Alternative getter which returns taggings directly as strings
     */
    public static List<String> getTaggingsStringList() {
        final ArrayList<String> stringList = new ArrayList<String>();
        for (Tagging tagging : taggings) {
            stringList.add(tagging.getTagString());
        }
        return stringList;
    }

    /**
     * Helper function to format tag history entry as a single string
     */
    public String getTagString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(isAdd ? "+" : "-");
        builder.append(" ");
        builder.append(person.getName().toString());
        builder.append(" ");
        builder.append("[");
        builder.append(tagChanged.tagName);
        builder.append("]");
        return builder.toString();
    }

}