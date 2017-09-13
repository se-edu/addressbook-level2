package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;

/**
 * sorts the Address Book
 */

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the AddressBook.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SORT_ACKNOWLEDGEMENT = "Address Book sorted as requested ...";

    @Override
    public CommandResult execute() {
        if (!addressBook.isEmpty())
        {
            addressBook.sort();
            return new CommandResult(MESSAGE_SORT_ACKNOWLEDGEMENT);
        }
        else
        {
            return new CommandResult(Messages.MESSAGE_SORTING_FAILED);
        }
    }

}
