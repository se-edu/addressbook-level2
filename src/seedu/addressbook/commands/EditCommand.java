package seedu.addressbook.commands;

/**
 * Edits a person's info in the address book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits a person's info the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix.\n"
            + "Parameters: INDEX [p]p/PHONE (and/or) [p]e/EMAIL (and/or) [p]a/ADDRESS (and/or) [t/TAG]...\n"
            + "Example: " + COMMAND_WORD
            + " 1 p/23456789 e/johndoe@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "Edited Person: %1$s";
    public static final String MESSAGE_INVALID_PERSON_DISPLAYED_INDEX = "The person index provided is invalid";

    public EditCommand() {
    }

    @Override
    public CommandResult execute() {
        return new CommandResult("Command under implementation.");
    }
}