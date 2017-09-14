package seedu.addressbook.ui;

import static seedu.addressbook.common.Messages.MESSAGE_GOODBYE;
import static seedu.addressbook.common.Messages.MESSAGE_INIT_FAILED;
import static seedu.addressbook.common.Messages.MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE;
import static seedu.addressbook.common.Messages.MESSAGE_USING_STORAGE_FILE;
import static seedu.addressbook.common.Messages.MESSAGE_WELCOME;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import seedu.addressbook.commands.CommandResult;
import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Text UI of the application.
 */
public class TextUi {

    private final Scanner in;
    private final PrintStream out;
    private Formatter formatter;

    public TextUi() {
        this(System.in, System.out);
    }

    public TextUi(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Returns true if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    private boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Returns true if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    private boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(formatter.getCommentLineFormatRegex());
    }

    /**
     * Prompts for the command and reads the text entered by the user.
     * Ignores empty, pure whitespace, and comment lines.
     * Echos the command back to the user.
     * @return command (full line) entered by the user
     */
    public String getUserCommand() {
        out.print(formatter.getLinePrefix() + "Enter command: ");
        String fullInputLine = in.nextLine();

        // silently consume all ignored lines
        while (shouldIgnore(fullInputLine)) {
            fullInputLine = in.nextLine();
        }

        showToUser("[Command entered:" + fullInputLine + "]");
        return fullInputLine;
    }


    public void showWelcomeMessage(String version, String storageFilePath) {
        String storageFileInfo = String.format(MESSAGE_USING_STORAGE_FILE, storageFilePath);
        showToUser(
                formatter.getDivider(),
                formatter.getDivider(),
                MESSAGE_WELCOME,
                version,
                MESSAGE_PROGRAM_LAUNCH_ARGS_USAGE,
                storageFileInfo,
                formatter.getDivider());
    }

    public void showGoodbyeMessage() {
        showToUser(MESSAGE_GOODBYE, formatter.getDivider(), formatter.getDivider());
    }


    public void showInitFailedMessage() {
        showToUser(MESSAGE_INIT_FAILED, formatter.getDivider(), formatter.getDivider());
    }

    /** Shows message(s) to the user */
    public void showToUser(String... message) {
        for (String m : message) {
            out.println(formatter.getLinePrefix() + m.replace("\n", formatter.getLS() + formatter.getLinePrefix()));
        }
    }

    /**
     * Shows the result of a command execution to the user. Includes additional formatting to demarcate different
     * command execution segments.
     */
    public void showResultToUser(CommandResult result) {
        final Optional<List<? extends ReadOnlyPerson>> resultPersons = result.getRelevantPersons();
        if (resultPersons.isPresent()) {
            showPersonListView(resultPersons.get());
        }
        showToUser(result.feedbackToUser, formatter.getDivider());
    }

    /**
     * Shows a list of persons to the user, formatted as an indexed list.
     * Private contact details are hidden.
     */
    private void showPersonListView(List<? extends ReadOnlyPerson> persons) {
        final List<String> formattedPersons = new ArrayList<>();
        for (ReadOnlyPerson person : persons) {
            formattedPersons.add(person.getAsTextHidePrivate());
        }
        showToUserAsIndexedList(formattedPersons);
    }

    /** Shows a list of strings to the user, formatted as an indexed list. */
    private void showToUserAsIndexedList(List<String> list) {
        showToUser(Formatter.getIndexedListForViewing(list));
    }


}
