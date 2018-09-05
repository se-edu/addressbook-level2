package seedu.addressbook.data.person;

public class Street {
    private static String street = null;

    public Street(String street) {
        this.street = street;
    }

    public static String getStreet() {
        return street;
    }
}
