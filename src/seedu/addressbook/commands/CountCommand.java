package seedu.addressbook.commands;


/**
 * Shows total number of persons in address book.
 */
public class CountCommand extends Command {

    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Counts the total number of persons "
            + "in the address book.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_COUNTED = "Total number of persons in your address book is: %1$s";

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_COUNTED, addressBook.getTotalPersons()));
    }
}
