package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;
import java.util.Optional;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /** The feedback message to be shown to the user. Contains a description of the execution result */
    private final String feedbackToUser;

    /** The list of persons that was produced by the command */
    private final List<? extends ReadOnlyPerson> relevantPersons;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
        relevantPersons = null;
    }

    public CommandResult(String feedbackToUser, List<? extends ReadOnlyPerson> relevantPersons) {
        this.feedbackToUser = feedbackToUser;
        this.relevantPersons = relevantPersons;
    }

    /**
     * Returns list of persons relevant to the command command result, if any.
     */
    public Optional<List<? extends ReadOnlyPerson>> getRelevantPersons() {
        return Optional.ofNullable(relevantPersons);
    }

}
