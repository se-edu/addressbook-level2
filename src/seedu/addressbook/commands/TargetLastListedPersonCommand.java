package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.regex.Pattern;

import static seedu.addressbook.ui.TextUi.DISPLAYED_INDEX_OFFSET;

/**
 * Superclass for commands targeting a person last listed in the ui.
 * Provides convenience methods to validate arguments and retrieve the targeted person from the last viewed list.
 */
public abstract class TargetLastListedPersonCommand extends Command {

    public static final Pattern ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public final int targetIndex;


    /**
     * @param targetIndex last visible listing index of the target person
     */
    protected TargetLastListedPersonCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }


    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyPerson getTargetPerson() throws IndexOutOfBoundsException {
        return relevantPersons.get(targetIndex - DISPLAYED_INDEX_OFFSET);
    }

}
