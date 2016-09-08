package seedu.addressbook.data.tag;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.Person;

public class Tagging {
	
	private String status;
	public Tagging(Tag tag, Person person, boolean add) {
		if (add) {
			this.status = "+ " + person.toString() + " " + tag.toString();
		} else {
			this.status = "- " + person.toString() + " " + tag.toString();
		}
	}
	public String getStatus(){
		return status;
	}
	
	public static void printTaggings(){
		for(int i=0; i<AddressBook.tagList.size(); i++){
			System.out.println(AddressBook.tagList.get(i).getStatus());
		}
	}
}
