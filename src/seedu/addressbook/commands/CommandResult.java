package seedu.addressbook.commands;

import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.List;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {
    private String feedbackToUser;
    private List<? extends ReadOnlyPerson> relevantPersons;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }

    public CommandResult(String feedbackToUser, List<? extends ReadOnlyPerson> relevantPersons) {
        this(feedbackToUser);
        this.relevantPersons = relevantPersons;
    }

    public String getFeedbackToUser() {
        return feedbackToUser;
    }


    public List<? extends ReadOnlyPerson> getRelevantPersons() {
        return relevantPersons;
    }

}
