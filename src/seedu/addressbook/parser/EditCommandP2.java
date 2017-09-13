package seedu.addressbook.parser;

import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.common.Messages;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.ReadOnlyPerson;
import seedu.addressbook.data.person.UniquePersonList;

import java.util.HashMap;

public class EditCommandP2 extends Command{

    public static final String COMMAND_WORD = "edit phase 2";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Phase two of edit will edit person " +
            "(specified by index) according to particulars included.\n"
            + "      Arguments similar to that of add command\n"
            + "Parameters: INDEX OPTIONAL[n/NAME, p/PHONE, e/EMAIL, a/ADDRESS]\n"
            + "Example: 3 n/Jon p/90000000";

    public static final String MESSAGE_SUCCESS = "Person edited";

    private HashMap<String, String> toChangeMap;

    public EditCommandP2(int index, String name, String phone, String email, String address) {
        super(index);
        toChangeMap = new HashMap<>();
        toChangeMap.put("name", name);
        toChangeMap.put("phone", phone);
        toChangeMap.put("email", email);
        toChangeMap.put("address", address);
    }

    @Override
    public CommandResult execute() {
        try {
            final ReadOnlyPerson target = getTargetPerson();
            addressBook.editPerson(target, toChangeMap);
            return new CommandResult(String.format(MESSAGE_SUCCESS, target));
        } catch (IndexOutOfBoundsException ie) {
            return new CommandResult(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        } catch (UniquePersonList.PersonNotFoundException pnfe) {
            return new CommandResult(Messages.MESSAGE_PERSON_NOT_IN_ADDRESSBOOK);
        } catch (IllegalValueException e) {
            return new CommandResult(Messages.MESSAGE_INVALID_PARTICULAR_FORMAT);
        }
    }
}
