package seedu.addressbook.data.person;

public class PostalCode {
	private String PostalCodeName;
	public PostalCode(String PostalCodeName){
		this.PostalCodeName = PostalCodeName;
	}
	public String toString(){
		return PostalCodeName;
	}
}
