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

    protected TextUi view;
    protected final int targetIndex;

    /**
     * @param targetIndex last visible listing index of the target person
     */
    protected TargetLastListedPersonCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public abstract void injectDependencies(TextUi ui, AddressBook addressBook);

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyPerson getTargetFromView() throws IndexOutOfBoundsException {
        return view.getPersonFromLastShownListing(targetIndex);
    }

}
