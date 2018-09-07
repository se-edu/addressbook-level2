package seedu.addressbook.ui;

import static seedu.addressbook.common.Messages.MESSAGE_GOODBYE;
import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;

public class Formatter {
    /** A decorative prefix added to the beginning of lines printed by AddressBook */
    private static final String LINE_PREFIX = "|| ";

    /** A platform independent line separator. */
    private static final String LS = System.lineSeparator();

    private static final String DIVIDER = "===================================================";

    /** Format of indexed list item */
    private static final String MESSAGE_INDEXED_LIST_ITEM = "\t%1$d. %2$s";

    public String formatFragments(String... message) {
        String stringChain = "";
        for (String m : message) {
            stringChain += (LINE_PREFIX + m.replace("\n", LS + LINE_PREFIX) + LS);
        }
        return stringChain;
    }

    public String formatEnterCommandPrompt(String enterCommandPrompt) {
        return LINE_PREFIX + enterCommandPrompt;
    }

    public String formatEnterCommandResponse(String enteredCommandResponse){
        enteredCommandResponse = "[Command entered:" + enteredCommandResponse + "]" + LS;
        return enteredCommandResponse;
    }

    public String formatWelcomeMessage(String version, String storageFileInfo) {
        return formatFragments(
                DIVIDER,
                DIVIDER,
                MESSAGE_WELCOME,
                version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo,
                DIVIDER);
    }

    public String formatGoodbyeMessage(String message) {
        return formatFragments(message, DIVIDER, DIVIDER);
    }

    public String formatResult(String feedbackToUser) {
        return formatFragments(feedbackToUser, DIVIDER);
    }
}
