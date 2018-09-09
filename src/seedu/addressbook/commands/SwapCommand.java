package seedu.addressbook.commands;

public class SwapCommand extends Command{
    public static final String COMMAND_WORD = "swap";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Swap the positions of two persons identified by indices in address book.\n"
            + "Parameters: INDEX, INDEX\n"
            + "Example: " + COMMAND_WORD + " 1,2";

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Swap successfully.";

}
