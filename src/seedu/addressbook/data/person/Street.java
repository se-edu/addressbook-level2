package seedu.addressbook.data.person;

public class Street {

    private String street;

    public Street(String street) {
        this.street = street.trim();
    }

    public String getstreet() {
        return street;
    }
}
