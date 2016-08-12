package seedu.addressbook.storage.jaxb;

import seedu.addressbook.model.IllegalValueException;
import seedu.addressbook.model.Tag;

import javax.xml.bind.annotation.XmlValue;

/**
 * JAXB-friendly adapted tag data holder class.
 */
public class AdaptedTag {

    @XmlValue
    public String tagName;

    /**
     * No-arg constructor for JAXB use.
     */
    public AdaptedTag() {}

    /**
     * Converts a given Tag into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedTag
     */
    public AdaptedTag(Tag source) {
        tagName = source.tagName;
    }

    /**
     * Converts this jaxb-friendly adapted tag object into the model's Tag object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Tag toModelType() throws IllegalValueException {
        return new Tag(tagName);
    }
}
