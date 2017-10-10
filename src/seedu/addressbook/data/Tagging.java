package seedu.addressbook.data;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.tag.Tag;

public class Tagging {
    private final Person person;
    private final Tag tag;
    private final String operation;

    public enum TagOperation {
        ADD, REMOVE
    }

    public Tagging(Person person, Tag tag, String operation) {
        this.person = person;
        this.tag = tag;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return operation + " " + person.getName().fullName + " " + tag.toString();
    }

}
