package seedu.addressbook.data.address;

public class unit {

    public String unitNumber;

    public unit(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    public final void setUnitNumber(String unitNumber) {
        this.unitNumber = unitNumber;
    }

    @Override
    public String toString() { return "Unit: " + unitNumber; }
}
