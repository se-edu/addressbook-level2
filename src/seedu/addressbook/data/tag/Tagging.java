package java.seedu.addressbook.data.tag;

public class Tagging {
    Tag tag; 
    Person person;
    String operation;

    Tagging (Tag tag, Person person, String operation) {
        this.tag = tag;
        this.person = person;
        this.operation = operation;
    }
}
