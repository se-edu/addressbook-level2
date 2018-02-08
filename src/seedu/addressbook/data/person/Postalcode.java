package seedu.addressbook.data.person;

/**
 * Represents a Person's address's postalcode in the address book.
 */
public class Postalcode {

    public final String value;

    /**
     * Validates given address's postalcode.
     */
    public Postalcode(String postalCode)  {
        this.value = postalCode;
    }

}
