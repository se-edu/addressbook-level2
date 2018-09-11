package seedu.addressbook.commands;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort base on selected attribute.\n"
            + "[p]p/PHONE [p]e/EMAIL [p]a/ADDRESS [t/TAG]"
            + "Example: Sort p";
    private static final String MESSAGE_SORT_SUCCESS = "Sort + ";


}
