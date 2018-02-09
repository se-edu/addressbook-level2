package seedu.addressbook.data.person;

public class Block{
	private String block;
	
	public Block(String block){
		this.block = block;
	}
	
	public String toString(){
		return this.block;
	}
	
	public String getBlock() {
		return this.block;
	}
}