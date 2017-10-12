package seedu.addressbook.data.tag;

import seedu.addressbook.data.person.Person;

/**
 * Represents the adding or deleting of a Tag that happened during a session
 * Association class between Person and the deleted/added Tag
 */
public class Tagging {

	private final Person person;
	private final Tag tag;
	private final boolean isAdd;

	public Tagging(Person person, Tag tag, boolean isAdd) {
		this.person = person;
		this.tag = tag;
		this.isAdd = isAdd;
	}

	public String toString() {
		return String.format("%c %s [%s]", isAdd ? '+' : '-', person.getName().fullName, tag.tagName);
	}
}
