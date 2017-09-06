package seedu.addressbook.data.person;

public class Street {
    private String value;

    public Street(String street) {
        value = street;
    }

    public String getStreet() {
        return value;
    }

    public boolean isValid() {
        return true;
    }
}
