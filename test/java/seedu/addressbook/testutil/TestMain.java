package seedu.addressbook.testutil;
import seedu.addressbook.data.person.ReadOnlyPerson;

import seedu.addressbook.commands.*;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.parser.Parser;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Test class for Main
 */
public class TestMain {

    private AddressBook addressBook;

    /** The list of person shown to the user most recently.  */
    private List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();

    public TestMain() {
        this.addressBook = new TypicalTestPersons().getTypicalAddressBook();
    }
    
    public List<? extends ReadOnlyPerson> getLastShownList() {
        return this.lastShownList;
    }
    
    public AddressBook getAddressBook() {
        return this.addressBook;
    }
    
    /** Reads the user command and executes it */
    public CommandResult runCommand(String inputCommand) {
        Command command = new Parser().parseCommand(inputCommand);
        CommandResult result = executeCommand(command);
        recordResult(result);
        return result;
    }

    /** Updates the {@link #lastShownList} if the result contains a list of Persons. */
    private void recordResult(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> personList = result.getRelevantPersons();
        if (personList.isPresent()) {
            lastShownList = personList.get();
        }
    }

    /**
     * Executes the command and returns the result.
     * 
     * @param command user command
     * @return result of the command
     */
    private CommandResult executeCommand(Command command)  {
        try {
            command.setData(addressBook, lastShownList);
            CommandResult result = command.execute();
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
