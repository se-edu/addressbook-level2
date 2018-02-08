package seedu.addressbook.data.person;

public class PostalCode {
    private static String postalCode;

    public PostalCode(String value) {
        this.postalCode = value;
    }
    public String getPostalCode() {
        return this.postalCode;
    }
}
