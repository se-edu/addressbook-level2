package seedu.addressbook.commands;

/**
 * Adds a specified number of persons to the address book.
 */
public class AddMultipleCommand extends Command {

    public static final String COMMAND_WORD = "addx";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Adds a specified number of persons to the address book. "
            + "Parameters: NUMBEROFPEOPLETOADD\n"
            + "Example: " + COMMAND_WORD
            + " 3";

    public int numOfPersons;

    /**
     * Basic constructor that adds persons one at a time.
     *
     * @param numOfPersons the number of people to be added.
     */
    public AddMultipleCommand(int numOfPersons) {
        this.numOfPersons = numOfPersons;
        System.out.println(this.numOfPersons);
    }

}
