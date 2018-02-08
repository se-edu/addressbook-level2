package seedu.addressbook.data.person;

/**
 * Represents a Person's postal code address in the address book.
 * Guarantees: immutable;
 */
public class PostalCode {
    private final String postalCode;

    public PostalCode(String postalCode){
        String trimmedPostalCode = postalCode;
        this.postalCode = trimmedPostalCode;
    }

    @Override
    public String toString(){
        return postalCode;
    }

    public String getPostalCode(){
        return postalCode;
    }
}
