package seedu.addressbook.data.person;

public class Block {
private String value;
	
	public Block(String value){
		this.value=value;
	}

	public String getBlockValue() {
		return this.value;
	}
	
	public void setBlockValue(String newValue){
		this.value=newValue;		
	}

}


