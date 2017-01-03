package seedu.addressbook.data.tag;

import seedu.addressbook.data.person.Person;

public class Tagging {

    private final String tagAction;
    private final Person person;
    private final Tag tag;
    
    public static final String ADD_TAG = "+";
    public static final String REMOVE_TAG = "-";
    
    public Tagging(String tagAction, Person person, Tag tag) {
        this.person = person;
        this.tag = tag;
        this.tagAction = tagAction;
    }
    
    public String toString() {
        if (tagAction == "ADD") {
            return ADD_TAG + " " + person.getName().toString() + " " + tag.toString(); 
        }
        else {
            return REMOVE_TAG + " " + person.getName().toString() + " " + tag.toString(); 
        }
    }
}
