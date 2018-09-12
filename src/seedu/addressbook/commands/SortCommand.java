package seedu.addressbook.commands;


import seedu.addressbook.data.person.UniquePersonList;
import seedu.addressbook.data.person.Person;



public class SortCommand extends Command {

	public static final String COMMAND_WORD = "sort";

	public static final String MESSAGE_SUCCESS = "AddressBook successfully sorted!";
	public static final String MESSAGE_USAGE = COMMAND_WORD
			+ ": Sorts all persons in the address book in alphabetical order of their names.\n"
			+ "Example: " + COMMAND_WORD;


	/*
	 * Think of alternative method rather than to use empty catch block
	 */
	@Override
	public CommandResult execute() {
		UniquePersonList listToSort = addressBook.getAllPersons();
		listToSort.sort();

		addressBook.clear();
		for (Person personToAdd : listToSort) {
			try {
				addressBook.addPerson(personToAdd);

			}
			catch (Exception e) {

			}
		}

		return new CommandResult(MESSAGE_SUCCESS);
	}
}
