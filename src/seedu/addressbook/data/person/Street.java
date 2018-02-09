package seedu.addressbook.data.person;

public class Street{
	private String street;
	
	public Street(String street){
		this.street = street;
	}
	
	public String toString(){
		return this.street;
	}
	
	public String getStreet() {
		return this.street;
	}
}