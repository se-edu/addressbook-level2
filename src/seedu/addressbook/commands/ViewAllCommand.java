package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.Password.PasswordStorage;
import seedu.addressbook.data.person.ReadOnlyPerson;


/**
 * Shows all details of the person identified using the last displayed index.
 * Private contact details are shown.
 */
public class ViewAllCommand extends Command {

    public static final String COMMAND_WORD = "viewall";
    public String userInputPassword;

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the non-private details of the person "
            + "identified by the index number in the last shown person listing.\n"
            + "Parameters: INDEX\n"
            + "Example: " + COMMAND_WORD + " 1 " + PasswordStorage.getExamplePassword();

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Viewing person: %1$s";


    public ViewAllCommand(int targetVisibleIndex, String userInputPassword) {
        super(targetVisibleIndex);
        this.userInputPassword = userInputPassword;

    }


    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            if (!addressBook.containsPerson(target)) {
                return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
            }
            if(!userInputPassword.equals(PasswordStorage.getActualPassword())){
                return new CommandResult(Messages.MESSAGE_WRONG_PASSWORD);
            }
            return new CommandResult(String.format(MESSAGE_VIEW_PERSON_DETAILS, target.getAsTextShowAll()));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }
}
