package seedu.addressbook.data.person;

public class PostalCode {
	private String value;
	
	public PostalCode(String value){
		this.value=value;
	}

	public String getPostalCodeValue() {
		return this.value;
	}
	
	public void setPostalCodeValue(String newValue){
		this.value=newValue;		
	}

}
