package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Superclass for commands targeting a person last listed in the ui.
 * Provides convenience methods to validate arguments and retrieve the targeted person from the last viewed list.
 */
public abstract class TargetLastListedPersonCommand implements Command {

    public static final Pattern ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    protected final TextUi view;
    protected final String args;

    /**
     * @param args full command args string from the user
     * @param view ui holding the previous viewed person listing
     */
    protected TargetLastListedPersonCommand(String args, TextUi view) {
        this.view = view;
        this.args = args;
    }

    public static boolean isValidArgs(String findArgs) {
        return findArgs.matches(ARGS_FORMAT.pattern());
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws NumberFormatException if the target index cannot be parsed into an integer
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyPerson extractTargetFromArgs() throws NumberFormatException, IndexOutOfBoundsException {
        final Matcher matcher = ARGS_FORMAT.matcher(args.trim());
        matcher.matches();
        final int targetDisplayedIndex = Integer.parseInt(matcher.group("targetIndex"));
        return view.getPersonFromLastShownListing(targetDisplayedIndex);
    }

}
