package seedu.addressbook.data.person;

public class PostalCode {
    public static String postalCode;

    public PostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public static String getPostalCode() {
        return postalCode;
    }
}
