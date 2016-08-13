package seedu.addressbook.commands;

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

    private final AddressBook addressBook;
    private final String args;

    /**
     * @param args full command args string from the user
     * @param addressBook address book person will be added to
     */
    public AddPersonCommand(String args, AddressBook addressBook) {
        this.addressBook = addressBook;
        this.args = args;
    }

    @Override
    public String execute() {
        if (!isValidArgs(args)) {
            return String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE);
        }
        try {
            final Person personToAdd = getPersonFromArgs();
            addressBook.addPerson(personToAdd);
            return String.format(MESSAGE_SUCCESS, personToAdd);

        } catch (UniquePersonList.DuplicatePersonException dpe) {
            return MESSAGE_DUPLICATE_PERSON;
        } catch (IllegalValueException ive) {
            return ive.getMessage();
        }
    }

    public static boolean isValidArgs(String args) {
        return args.trim().matches(ARGS_FORMAT.pattern());
    }

    /**
     * Extracts the specified person to add from the given arguments.
     * 
     * @throws IllegalValueException if any person data field constraint is not fulfilled
     */
    private Person getPersonFromArgs() throws IllegalValueException {
        final Matcher matcher = ARGS_FORMAT.matcher(args.trim());
        matcher.matches();

        final Name name = new Name(matcher.group("name"));
        final Phone phone = new Phone(matcher.group("phone"), isPrivatePrefixPresent(matcher.group("isPhonePrivate")));
        final Email email = new Email(matcher.group("email"), isPrivatePrefixPresent(matcher.group("isEmailPrivate")));
        final Address address = new Address(matcher.group("address"),
                isPrivatePrefixPresent(matcher.group("isAddressPrivate")));

        return new Person(name, phone, email, address, getTagsFromArgs());
    }

    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the new person's tags from the arguments.
     */
    public UniqueTagList getTagsFromArgs() throws IllegalValueException {
        final Matcher matcher = ARGS_FORMAT.matcher(args.trim());
        matcher.matches();

        // pull out tag arguments string for parsing
        final String tagArguments = matcher.group("tagArguments");
        if (tagArguments.isEmpty()) {
            return new UniqueTagList();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));

        // merge duplicate tags
        final Set<Tag> tags = new HashSet<>();
        for (String tagString : tagStrings) {
            tags.add(new Tag(tagString));
        }
        // tag list prepared
        return new UniqueTagList(tags);
    }

}
