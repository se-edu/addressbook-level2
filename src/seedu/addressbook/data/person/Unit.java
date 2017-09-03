package seedu.addressbook.data.person;
/**
 * Represents a Unit in a Person's address in the address book.
 */

public class Unit {
    private String unitNumber;

    public Unit() {
        unitNumber = "";
    }

    public void setUnitNumber(String u) {
        unitNumber = u;
    }

    public String getUnitNumber() {
        return unitNumber;
    }
}
