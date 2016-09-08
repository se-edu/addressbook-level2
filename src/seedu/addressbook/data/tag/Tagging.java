package seedu.addressbook.data.tag;

import seedu.addressbook.data.person.Person;

public class Tagging {
	
	String status;
	public Tagging(Tag tag, Person person, boolean add) {
		if (add) {
			this.status = "+ " + person.toString() + " " + tag.toString();
		} else {
			this.status = "- " + person.toString() + " " + tag.toString();
		}
	}
}
