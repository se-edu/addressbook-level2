package seedu.addressbook.commands;
/**
 * Add a reminder for user
 */

public class Reminder extends Command {

    public static final String COMMAND_WORD = "reminder";

    public static final String REMINDER_SUCCESS = "your reminder has been added!";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Add your reminder in the program\n"
            + "Example: " + COMMAND_WORD + " give Daphene 20 dollars on Feb 20";

    public String reminder;


    public Reminder(String reminder) {
        this.reminder = reminder;
    }

    @Override
    public CommandResult execute() {
        addressBook.setReminderMessage(reminder);
        return new CommandResult(REMINDER_SUCCESS);
    }

}