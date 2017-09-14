package seedu.addressbook.commands;


/**
 * Sorts all the persons in the address book by alphabetical order
 */

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts all persons in the address by alphabetical order. \n"
            + "Example: " + COMMAND_WORD;
}
