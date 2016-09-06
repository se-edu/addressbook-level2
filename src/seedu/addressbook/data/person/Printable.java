package seedu.addressbook.data.person;

/**
 * A read-only immutable interface for a Person's contact instances.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface Printable {
    public String getPrintableString();
}
