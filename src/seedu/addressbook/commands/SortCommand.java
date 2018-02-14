package seedu.addressbook.commands;
/**
 * Sorts all persons in the address book by name.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts the list of all persons in the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Addressbook sorted!";

    @Override
    public CommandResult execute() {
        addressBook.sortPersonsByName();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
