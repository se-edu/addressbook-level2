package seedu.addressbook.data.person;

/**
 * Represents a Person's street address in the address book.
 * Guarantees: immutable;
 */
public class Street {
    private final String street;

    public Street(String street){
        String trimmedStreet = street.trim();
        this.street = trimmedStreet;
    }

    @Override
    public String toString(){
        return street;
    }

    public String getBlock(){
        return street;
    }
}
