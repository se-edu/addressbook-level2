package seedu.addressbook.data.person;

import java.util.Set;

import seedu.addressbook.data.tag.Tag;

/**
 * A read-only immutable interface for a Person in the addressbook.
 * Implementations should guarantee: details are present and not null, field values are validated.
 */
public interface ReadOnlyPerson {

    Name getName();
    Phone getPhone();
    Email getEmail();
    Address getAddress();

    /**
     * Returns a new TagSet that is a deep copy of the internal TagSet,
     * changes on the returned set will not affect the person's internal tags.
     */
    Set<Tag> getTags();

    /**
     * Returns true if both persons have the same identity fields (name and telephone).
     */
    default boolean isSamePerson(ReadOnlyPerson other) {
        return (other == this)
                || (other != null
                    && other.getName().equals(this.getName())
                    && other.getPhone().equals(this.getPhone()));
    }

    /**
     * Returns true if all data in this object is the same as that in another
     * (Note: interfaces cannot override .equals)
     */
    default boolean hasSameData(ReadOnlyPerson other) {
        return other == this // short circuit if same object
                || (other != null // this is first to avoid NPE below
                    && other.getName().equals(this.getName()) // state checks here onwards
                    && other.getPhone().equals(this.getPhone())
                    && other.getEmail().equals(this.getEmail())
                    && other.getAddress().equals(this.getAddress())
                    && other.getTags().equals(this.getTags()));
    }

    /**
     * Formats the person as text, showing all contact details.
     */
    default String getAsTextShowAll() {
        final StringBuilder builder = new StringBuilder();
        final String detailIsPrivate = "(private) ";
        builder.append(getName())
                .append(" Phone: ");
        if (getPhone().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getPhone())
                .append(" Email: ");
        if (getEmail().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getEmail())
                .append(" Address: ");
        if (getAddress().isPrivate()) {
            builder.append(detailIsPrivate);
        }
        builder.append(getAddress())
                .append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }

    /**
     * Formats a person as text, showing only non-private contact details.
     */
    default String getAsTextHidePrivate() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName());
        if (!getPhone().isPrivate()) {
            builder.append(" Phone: ").append(getPhone());
        }
        if (!getEmail().isPrivate()) {
            builder.append(" Email: ").append(getEmail());
        }
        if (!getAddress().isPrivate()) {
            builder.append(" Address: ").append(getAddress());
        }
        builder.append(" Tags: ");
        for (Tag tag : getTags()) {
            builder.append(tag);
        }
        return builder.toString();
    }
}
