package seedu.addressbook.data.person;

public class PostalCode {
    private static String postalCode = null;

    public PostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public static String getPostalCode() {
        return postalCode;
    }
}
