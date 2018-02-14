package seedu.addressbook.data.reminder;

public class Reminder {
    public String reminderMessage;

    public Reminder(String message) {
        this.reminderMessage = message;
    }


    @Override
    public String toString() {
        return reminderMessage;
    }
}
