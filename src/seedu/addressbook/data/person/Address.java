package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";
    private static final int BLOCK=1;
    private static final int STREET=2;
    private static final int UNIT=3;
    private static final int POSTAL_CODE=4;
    

    private String value;
    private boolean isPrivate;
    
    private Block block;
    private Street street;
    private Unit unit;
    private PostalCode postalCode;
    
    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        this.isPrivate = isPrivate;
        if (!isValidAddress(address)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }
        this.value = address;
        block=new Block(this.segmentAddress(address, BLOCK));
        unit=new Unit(this.segmentAddress(address, UNIT));
        street=new Street(this.segmentAddress(address, STREET));
        postalCode=new PostalCode(this.segmentAddress(address, POSTAL_CODE));
    }
    
    
    
    /**
     * Segments and returns either BLOCK, STREET, UNIT, or POSTAL_CODE  
     */
    
    public String segmentAddress(String address, int segment){
    	int commaCount=0;
    	int beginIndex=2;
    	int endIndex;
    	int i;
    	String target="";
    	
    	for(i=2;i<address.length();i++){
    		if(address.charAt(i)==','){
    			commaCount++;
    			if(commaCount==segment){
    				endIndex=i;
    				target= address.substring(beginIndex, endIndex);
    				return target;
    			}
    			beginIndex=i+2;
    		}	
    	}
    	endIndex=i;
		target=address.substring(beginIndex, endIndex);
		return target;
    }
    
    public Block getBlock(){
    	return this.block; 	
    }
    
    public Street getStreet(){
    	return this.street;
    }
    
    public Unit getUnit(){
    	return this.unit;
    }
    
    public PostalCode getPostalCode(){
    	return this.postalCode;
    }

    /**
     * Returns true if a given string is a valid person email.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}