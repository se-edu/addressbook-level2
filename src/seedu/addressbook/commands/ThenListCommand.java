package seedu.addressbook.commands;

import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

public class ThenListCommand extends Command {

    private Command prevCommand;

    public static ThenListCommand thenList(Command prevCommand) {
        return new ThenListCommand(prevCommand);
    }

    private ThenListCommand(Command prevCommand) {
        this.prevCommand = prevCommand;
    }

    @Override
    public CommandResult execute() {
        this.prevCommand.execute();

        ListCommand listCmd = new ListCommand();
        listCmd.setData(this.prevCommand.addressBook, this.prevCommand.relevantPersons);

        return listCmd.execute();

    }

    @Override
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        prevCommand.addressBook = addressBook;
        prevCommand.relevantPersons = relevantPersons;
    }


}
