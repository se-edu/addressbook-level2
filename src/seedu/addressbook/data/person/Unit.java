package seedu.addressbook.data.person;

public class Unit {
    private static String street = null;

    public Unit(String street) {
        this.street = street;
    }

    public static String getStreet() {
        return street;
    }
}