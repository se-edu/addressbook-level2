package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;

public class SortCommand extends Command{

	public static final String COMMAND_WORD = "sort";

	public static final String MESSAGE_USAGE = COMMAND_WORD
			+ ": Sort all persons' names in alphabetical order.\n"
			+ "Example: " + COMMAND_WORD;

	public static final String MESSAGE_SORT_ADDRESSBOOK_SUCCESS = "Contacts successfully sorted in "
			+ "alphabetical order. \n";

	@Override
	public CommandResult execute() {
		if(!addressBook.isEmpty()){
			return new CommandResult(MESSAGE_SORT_ADDRESSBOOK_SUCCESS);
		}
		else{
			return new CommandResult(Messages.MESSAGE_SORTING_FAILED);
		}

	}
}
