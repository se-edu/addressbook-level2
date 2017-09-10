package seedu.addressbook.data.person;

public class PostalCode {
    private String postalNumber = "";
    public PostalCode(String postalNumber) {
        this.postalNumber = postalNumber;
    }
    public String toString() {
        return postalNumber;
    }
}
