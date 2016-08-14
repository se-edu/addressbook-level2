package seedu.addressbook.commands;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }


}
