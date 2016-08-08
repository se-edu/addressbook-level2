package seedu.addressbook.commands;

import seedu.addressbook.TextUi;

import static seedu.addressbook.TextUi.LS;

/**
 * Terminates the program.
 */
public class ExitCommand implements Command {

    public static final String COMMAND_WORD = "exit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Exits the program."
            + LS + "Example: " + COMMAND_WORD;

    private final TextUi ui;

    /**
     * @param ui to show feedback before exiting.
     */
    public ExitCommand(TextUi ui) {
        this.ui = ui;
    }

    @Override
    public String execute() {
        ui.showGoodbyeMessage();
        System.exit(0);
        throw new InternalError(); // should never reach this line.
    }
}
