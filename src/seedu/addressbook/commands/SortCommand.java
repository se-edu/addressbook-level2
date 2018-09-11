package seedu.addressbook.commands;

/**
 * Sorts list of persons in address book
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in address book. \n"
            + "Example: " + COMMAND_WORD;

    @Override
    public CommandResult execute() {
        addressBook.sort();
        return new CommandResult("Address book has been sorted");
    }
}
