package seedu.addressbook.commands;

import seedu.addressbook.common.Messages;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.text.View;

import static seedu.addressbook.ui.TextUi.DISPLAYED_INDEX_OFFSET;

/**
 * Represents an executable command.
 */
public class Command {
    public final String feedbackToUser;

    private AddressBook addressBook;
    private List<? extends ReadOnlyPerson> relevantPersons;

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

    protected Command() {
        this.feedbackToUser = null;
    }

    public Command(AddCommand addCommand) {
        this.addCommand = addCommand;
        this.feedbackToUser = null;
    }

    public Command(ClearCommand clearCommand) {
        this.clearCommand = clearCommand;
        this.feedbackToUser = null;
    }

    public Command(DeleteCommand deleteCommand) {
        this.deleteCommand = deleteCommand;
        this.feedbackToUser = null;
    }

    public Command(ExitCommand exitCommand) {
        this.exitCommand = exitCommand;
        this.feedbackToUser = null;
    }

    public Command(FindCommand findCommand) {
        this.findCommand = findCommand;
        this.feedbackToUser = null;
    }

    public Command(HelpCommand helpCommand) {
        this.helpCommand = helpCommand;
        this.feedbackToUser = null;
    }

    public Command(IncorrectCommand incorrectCommand) {
        this.incorrectCommand = incorrectCommand;
        this.feedbackToUser = incorrectCommand.feedbackToUser;
    }

    public Command(ListCommand listCommand) {
        this.listCommand = listCommand;
        this.feedbackToUser = null;
    }

    public Command(ViewAllCommand viewAllCommand) {
        this.viewAllCommand = viewAllCommand;
        this.feedbackToUser = null;
    }

    public Command(ViewCommand viewCommand) {
        this.viewCommand = viewCommand;
        this.feedbackToUser = null;
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
        if (addCommand != null) {
            return addCommand.execute(addressBook);
        } else if (clearCommand != null) {
            return clearCommand.execute(addressBook);
        } else if (deleteCommand != null) {
            return deleteCommand.execute(addressBook, relevantPersons);
        } else if (exitCommand != null) {
            return exitCommand.execute();
        } else if (findCommand != null) {
            return findCommand.execute(addressBook);
        } else if (helpCommand != null) {
            return helpCommand.execute();
        } else if (incorrectCommand != null) {
            return incorrectCommand.execute();
        } else if (listCommand != null) {
            return listCommand.execute(addressBook);
        } else if (viewAllCommand != null) {
            return viewAllCommand.execute(addressBook, relevantPersons);
        } else if (viewCommand != null) {
            return viewCommand.execute(addressBook, relevantPersons);
        } else {
            throw new UnsupportedOperationException("None of the commands are valid.");
        }
    };

    public Class getCommandClass() {
        if (addCommand != null) {
            return addCommand.getClass();
        } else if (clearCommand != null) {
            return clearCommand.getClass();
        } else if (deleteCommand != null) {
            return deleteCommand.getClass();
        } else if (exitCommand != null) {
            return exitCommand.getClass();
        } else if (findCommand != null) {
            return findCommand.getClass();
        } else if (helpCommand != null) {
            return helpCommand.getClass();
        } else if (incorrectCommand != null) {
            return incorrectCommand.getClass();
        } else if (listCommand != null) {
            return listCommand.getClass();
        } else if (viewAllCommand != null) {
            return viewAllCommand.getClass();
        } else if (viewCommand != null) {
            return viewCommand.getClass();
        } else {
            throw new UnsupportedOperationException("None of the commands are valid.");
        }
    }

    /**
     * Supplies the data the command will operate on.
     */
    public void setData(AddressBook addressBook, List<? extends ReadOnlyPerson> relevantPersons) {
        this.addressBook = addressBook;
        this.relevantPersons = relevantPersons;
    }

    /**
     * Returns a copy of keywords from find command.
     */
    public Set<String> getKeywords() {
        if (findCommand != null) {
            return findCommand.getKeywords();
        } else {
            return null;
        }
    }

    /**
     * Returns a copy of to add person from add command.
     */
    public ReadOnlyPerson getPerson() {
        if (addCommand != null) {
            return addCommand.getPerson();
        } else {
            return null;
        }
    }

    public int getTargetIndex() {
        if (deleteCommand != null) {
            return deleteCommand.getTargetIndex();
        } else if (viewAllCommand != null) {
            return viewAllCommand.getTargetIndex();
        } else if (viewCommand != null) {
            return viewCommand.getTargetIndex();
        } else {
            return -1;
        }
    }

    public void setTargetIndex(int targetIndex) {
        if (deleteCommand != null) {
            deleteCommand.setTargetIndex(targetIndex);
        } else if (viewAllCommand != null) {
            viewAllCommand.setTargetIndex(targetIndex);
        } else if (viewCommand != null) {
            viewCommand.setTargetIndex(targetIndex);
        }
    }
}
