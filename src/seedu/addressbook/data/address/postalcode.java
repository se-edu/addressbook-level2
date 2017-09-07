package seedu.addressbook.data.address;

public class postalcode {

    public String postalcodeNumber;

    public postalcode(String postalcodeNumber) {
        this.postalcodeNumber = postalcodeNumber;
    }

    public final void setPostalcodeNumber(String postalcodeNumber) {
        this.postalcodeNumber = postalcodeNumber;
    }

    @Override
    public String toString() { return "Postalcode: " + postalcodeNumber; }
}
