package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.List;

import javax.swing.text.View;

import static seedu.addressbook.ui.TextUi.DISPLAYED_INDEX_OFFSET;

/**
 * Represents an executable command.
 */
public class Command {
    protected AddressBook addressBook;
    protected List<? extends ReadOnlyPerson> relevantPersons;
    private int targetIndex = -1;

    private AddCommand addCommand;
    private ClearCommand clearCommand;
    private DeleteCommand deleteCommand;
    private ExitCommand exitCommand;
    private FindCommand findCommand;
    private HelpCommand helpCommand;
    private IncorrectCommand incorrectCommand;
    private ListCommand listCommand;
    private ViewAllCommand viewAllCommand;
    private ViewCommand viewCommand;

    /**
     * @param targetIndex last visible listing index of the target person
     */
    public Command(int targetIndex) {
        this.setTargetIndex(targetIndex);
    }

    protected Command() {
    }

    public Command(AddCommand addCommand) {
        this.addCommand = addCommand;
    }

    public Command(ClearCommand clearCommand) {
        this.clearCommand = clearCommand;
    }

    public Command(DeleteCommand deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    public Command(ExitCommand exitCommand) {
        this.exitCommand = exitCommand;
    }

    public Command(FindCommand findCommand) {
        this.findCommand = findCommand;
    }

    public Command(HelpCommand helpCommand) {
        this.helpCommand = helpCommand;
    }

    public Command(IncorrectCommand incorrectCommand) {
        this.incorrectCommand = incorrectCommand;
    }

    public Command(ListCommand listCommand) {
        this.listCommand = listCommand;
    }

    public Command(ViewAllCommand viewAllCommand) {
        this.viewAllCommand = viewAllCommand;
    }

    public Command(ViewCommand viewCommand) {
        this.viewCommand = viewCommand;
    }

    /**
     * Constructs a feedback message to summarise an operation that displayed a listing of persons.
     *
     * @param personsDisplayed used to generate summary
     * @return summary message for persons displayed
     */
    public static String getMessageForPersonListShownSummary(List<? extends ReadOnlyPerson> personsDisplayed) {
        return String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, personsDisplayed.size());
    }

    /**
     * Executes the command and returns the result.
     */
    public CommandResult execute(){
        throw new UnsupportedOperationException("This method is to be implemented by child classes");
    };

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
        this.relevantPersons = relevantPersons;
    }

    /**
     * Extracts the the target person in the last shown list from the given arguments.
     *
     * @throws IndexOutOfBoundsException if the target index is out of bounds of the last viewed listing
     */
    protected ReadOnlyPerson getTargetPerson() throws IndexOutOfBoundsException {
        return relevantPersons.get(getTargetIndex() - DISPLAYED_INDEX_OFFSET);
    }

    public int getTargetIndex() {
        return targetIndex;
    }

    public void setTargetIndex(int targetIndex) {
        this.targetIndex = targetIndex;
    }
}
