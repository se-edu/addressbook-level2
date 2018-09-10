package seedu.addressbook.commands;

/**
 * Counts the number of persons in the address book
 */
public class CountCommand extends Command {

    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Gives you the number of persons in the address book. \n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        int size = addressBook.size();
        return new CommandResult(getMessageForPersonCountSummary(size), size);
    }
}
