package seedu.addressbook.data.person;

public class Unit {
	private String UnitName;
	public Unit(String UnitName){
		this.UnitName = UnitName;
	}
	public String toString(){
		return UnitName;
	}
<<<<<<< HEAD
	@Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Unit // instanceof handles nulls
                && this.UnitName.equals(((Unit) other).UnitName)); // state check
    }
=======
>>>>>>> e602bee9bd203b463eceba88a251f75c3870922c
}
