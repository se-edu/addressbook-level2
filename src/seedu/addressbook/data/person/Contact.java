package seedu.addressbook.data.person;

public class Contact {

	public String value;
	protected boolean isPrivate;

	public Contact(String value, boolean isPrivate) {
		this.value = value.trim();
		this.isPrivate = isPrivate;
	}
}
