package seedu.addressbook.data.person;

public abstract class Contact {
	
    private String value;
	
    public String toString() {
        return value;
    }
    
    public void setValue(String input) {
    	value = input;
    }
    
}
