package seedu.addressbook.storage.jaxb;

import seedu.addressbook.data.reminder.Reminder;

import javax.xml.bind.annotation.XmlValue;

public class AdaptedReminder {

    @XmlValue

    public String reminderMessage;

    public AdaptedReminder() {}

    public AdaptedReminder(String source) {
        reminderMessage = source;
    }

    public Reminder toModelType() {
        return new Reminder(reminderMessage);
    }

}