package seedu.addressbook.data.person;

public interface Printable {
	default String getPrintableString(){
		return null;
	}
}
