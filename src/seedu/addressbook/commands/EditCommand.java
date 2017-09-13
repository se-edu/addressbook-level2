package seedu.addressbook.commands;

import seedu.addressbook.data.person.Person;
import seedu.addressbook.data.person.UniquePersonList;

public class EditCommand extends Command{

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a person based on his name. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n"
            + "Parameters: NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "Person editted: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    //private final Person toEdit;

    public EditCommand(){}

    @Override
    public CommandResult execute() {
        return new CommandResult(String.format(MESSAGE_SUCCESS, "Dummy!"));
    }
}
