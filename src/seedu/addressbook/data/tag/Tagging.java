package seedu.addressbook.data.tag;

import seedu.addressbook.data.person.Person;

public class Tagging {
    private Person person;
    private Tag tag;
    private String string;

    public Tagging (Person person, Tag tag, String operator) {
        this.person = person;
        this.tag = tag;
        this.string = operator;
    }

    public String toString() {
        return string + " " + person.getName() + " " + tag.toString();
    }
}
