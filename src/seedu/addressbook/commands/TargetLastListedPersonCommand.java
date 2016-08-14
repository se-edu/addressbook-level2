package seedu.addressbook.commands;

import seedu.addressbook.model.AddressBook;
import seedu.addressbook.model.person.ReadOnlyPerson;

import java.util.List;
import java.util.regex.Pattern;

import static seedu.addressbook.TextUi.DISPLAYED_INDEX_OFFSET;

/**
 * Superclass for commands targeting a person last listed in the ui.
 * Provides convenience methods to validate arguments and retrieve the targeted person from the last viewed list.
 */
public abstract class TargetLastListedPersonCommand implements Command {

    public static final Pattern ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    protected final int targetIndex;
    protected List<? extends ReadOnlyPerson> relevantPersons;

    /**
     * @param targetIndex last visible listing index of the target person
     */
    protected TargetLastListedPersonCommand(int targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public abstract void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons);

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyPerson getTargetFromView() throws IndexOutOfBoundsException {
        return relevantPersons.get(targetIndex - DISPLAYED_INDEX_OFFSET);
    }

}
