package seedu.addressbook.data.person;

public class Street {
private String value;
	
	public Street(String value){
		this.value=value;
	}

	public String getStreetValue() {
		return this.value;
	}
	
	public void setStreetValue(String newValue){
		this.value=newValue;		
	}

}


