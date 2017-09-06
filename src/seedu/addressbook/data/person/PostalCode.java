package seedu.addressbook.data.person;

public class PostalCode {
    private String value;

    public PostalCode(String postalCode) {
        value = postalCode;
    }

    public String getPostalCode() {
        return value;
    }

    public boolean isValid() {
        for(int i = 0; i < value.length(); i++) {
            if(!Character.isDigit(value.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
