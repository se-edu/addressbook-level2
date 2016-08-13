package seedu.addressbook.commands;

import seedu.addressbook.TextUi;
import seedu.addressbook.Utils;
import seedu.addressbook.model.*;
import seedu.addressbook.model.person.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.addressbook.TextUi.LS;
import static seedu.addressbook.TextUi.MESSAGE_INVALID_COMMAND_FORMAT;

/**
 * Adds a person to the address book.
 */
public class AddPersonCommand implements Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Contact details can be marked private by prepending 'p' to the prefix."
            + LS + "Parameters: NAME [p]p/PHONE [p]e/EMAIL [p]a/ADDRESS  [t/TAG]..."
            + LS + "Example: " + COMMAND_WORD
            + " John Doe p/98765432 e/johnd@gmail.com a/311, Clementi Ave 2, #02-25 t/friends t/owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    public static final Pattern ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>[^/]+)"
                            + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)"
                            + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)"
                            + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)"
                            + "(?<tagArguments>(?: t/[^/]+)*)"); // variable number of tags

    private AddressBook addressBook;
    private final Person toAdd;

    public AddPersonCommand(Person toAdd) {
        this.toAdd = toAdd;
    }

    @Override
    public void injectDependencies(TextUi ui, AddressBook addressBook) {
        this.addressBook = addressBook;
    }

    @Override
    public String execute() {
        Utils.assertNotNull(addressBook);
        try {
            addressBook.addPerson(toAdd);
            return String.format(MESSAGE_SUCCESS, toAdd);
        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return MESSAGE_DUPLICATE_PERSON;
        }
    }

}
