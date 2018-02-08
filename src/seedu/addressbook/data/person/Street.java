package seedu.addressbook.data.person;

/**
 * Represents a Person's address's street in the address book.
 */
public class Street {

    public final String value;

    /**
     * Validates given address's street.
     */
    public Street(String street) {
        this.value = street;
    }

}
