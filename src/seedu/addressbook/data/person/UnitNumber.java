package seedu.addressbook.data.person;

public class UnitNumber{
	private String unitNumber;
	
	public UnitNumber(String unitNumber){
		this.unitNumber = unitNumber;
	}
	
	public String toString(){
		return this.unitNumber;
	}
	
	public String getUnitNumber() {
		return this.unitNumber;
	}
}