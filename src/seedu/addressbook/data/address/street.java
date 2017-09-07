package seedu.addressbook.data.address;

public class street {

    public String streetName;

    public street (String streetName) {
        this.streetName = streetName;
    }

    public final void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    @Override
    public String toString() { return "Street: " + streetName; }
}
