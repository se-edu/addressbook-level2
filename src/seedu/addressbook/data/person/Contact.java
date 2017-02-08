package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

public class Contact {
	
	  public final String value;
	  private boolean isPrivate;
	   
	  public Contact(String value,boolean isPrivate,String Reg,String problem) throws IllegalValueException{
		   if (!isValid(value,Reg)) {
	            throw new IllegalValueException(problem);
	        }
		   this.value=value;
		   this.isPrivate=isPrivate;
		   
	}

	private boolean isValid(String test, String Reg) {
		// TODO Auto-generated method stub
		return test.matches(Reg);
	}


	@Override
	public String toString() {
	    return value;
	}
	
	
	
	@Override
	public int hashCode() {
	    return value.hashCode();
	}
	
	public boolean isPrivate() {
	    return isPrivate;
	}
}
