package seedu.addressbook.commands;


/**
 * Represents an incorrect command. Upon execution, produces some feedback to the user.
 */
public class IncorrectCommand extends Command{

    public final String feedbackToUser;

    public IncorrectCommand(String feedbackToUser){
        this.feedbackToUser = feedbackToUser;
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute() {
        return new CommandResult(feedbackToUser);
    }

}
