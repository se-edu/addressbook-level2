package seedu.addressbook.commands;

import seedu.addressbook.data.person.UniquePersonList;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts the current list of people by a property. "
            + "Parameters: PROPERTY ORDER\n"
            + "Example: " + COMMAND_WORD
            + " name -1";

    public static final String MESSAGE_SUCCESS = "List sorted by: $s in %s order";

    private String propertyName;

    private boolean ascending;

    public SortCommand(String propertyName, int ascending) {
        this.propertyName = propertyName.toLowerCase().trim();
        this.ascending = (ascending > 0);
    }

    @Override
    public CommandResult execute() {
        // TO-DO: Add implementation (in a try-catch if required)
        return new CommandResult(String.format(MESSAGE_SUCCESS,
                propertyName,
                (ascending) ? "ascending" : "descending"));
    }

}
