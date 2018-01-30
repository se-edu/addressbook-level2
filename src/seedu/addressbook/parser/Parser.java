package seedu.addressbook.parser;

import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.addressbook.common.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.addressbook.commands.AddCommand;
import seedu.addressbook.commands.AggregateCommand;
import seedu.addressbook.commands.ClearCommand;
import seedu.addressbook.commands.Command;
import seedu.addressbook.commands.DeleteCommand;
import seedu.addressbook.commands.ExitCommand;
import seedu.addressbook.commands.FindCommand;
import seedu.addressbook.commands.HelpCommand;
import seedu.addressbook.commands.IncorrectCommand;
import seedu.addressbook.commands.ListCommand;
import seedu.addressbook.commands.ViewAllCommand;
import seedu.addressbook.commands.ViewCommand;
import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Parses user input.
 */
public class Parser {

    public static final Pattern PERSON_INDEX_ARGS_FORMAT = Pattern.compile("(?<targetIndex>.+)");

    public static final Pattern KEYWORDS_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    public static final Pattern PERSON_DATA_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile("(?<name>[^/]+)"
                    + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)"
                    + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)"
                    + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)"); // variable number of tags


    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }

    /**
     * Used for initial separation of command word and args.
     */
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public Parser() {}

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public AggregateCommand parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
            return new AggregateCommand(incorrectCommand);
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ClearCommand.COMMAND_WORD:
            return new AggregateCommand(new ClearCommand());

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case ListCommand.COMMAND_WORD:
            return new AggregateCommand(new ListCommand());

        case ViewCommand.COMMAND_WORD:
            return prepareView(arguments);

        case ViewAllCommand.COMMAND_WORD:
            return prepareViewAll(arguments);

        case ExitCommand.COMMAND_WORD:
            return new AggregateCommand(new ExitCommand());

        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new AggregateCommand(new HelpCommand());
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private AggregateCommand prepareAdd(String args) {
        final Matcher matcher = PERSON_DATA_ARGS_FORMAT.matcher(args.trim());
        // Validate arg string format
        if (!matcher.matches()) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
            return new AggregateCommand(incorrectCommand);
        }
        try {
            AddCommand addCommand = new AddCommand(
                    matcher.group("name"),

                    matcher.group("phone"),
                    isPrivatePrefixPresent(matcher.group("isPhonePrivate")),

                    matcher.group("email"),
                    isPrivatePrefixPresent(matcher.group("isEmailPrivate")),

                    matcher.group("address"),
                    isPrivatePrefixPresent(matcher.group("isAddressPrivate")),

                    getTagsFromArgs(matcher.group("tagArguments"))
            );
            return new AggregateCommand(addCommand);
        } catch (IllegalValueException ive) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(ive.getMessage());
            return new AggregateCommand(incorrectCommand);
        }
    }

    /**
     * Returns true if the private prefix is present for a contact detail in the add command's arguments string.
     */
    private static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the new person's tags from the add command's tag arguments string.
     * Merges duplicate tag strings.
     */
    private static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
        return new HashSet<>(tagStrings);
    }


    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private AggregateCommand prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            DeleteCommand deleteCommand = new DeleteCommand(targetIndex);
            return new AggregateCommand(deleteCommand);
        } catch (ParseException pe) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
            return new AggregateCommand(incorrectCommand);
        } catch (NumberFormatException nfe) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            return new AggregateCommand(incorrectCommand);
        }
    }

    /**
     * Parses arguments in the context of the view command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private AggregateCommand prepareView(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            ViewCommand viewCommand = new ViewCommand(targetIndex);
            return new AggregateCommand(viewCommand);
        } catch (ParseException pe) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewCommand.MESSAGE_USAGE));
            return new AggregateCommand(incorrectCommand);
        } catch (NumberFormatException nfe) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            return new AggregateCommand(incorrectCommand);
        }
    }

    /**
     * Parses arguments in the context of the view all command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private AggregateCommand prepareViewAll(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            ViewAllCommand viewAllCommand = new ViewAllCommand(targetIndex);
            return new AggregateCommand(viewAllCommand);
        } catch (ParseException pe) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewAllCommand.MESSAGE_USAGE));
            return new AggregateCommand(incorrectCommand);
        } catch (NumberFormatException nfe) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
            return new AggregateCommand(incorrectCommand);
        }
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    private int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = PERSON_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }


    /**
     * Parses arguments in the context of the find person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private AggregateCommand prepareFind(String args) {
        final Matcher matcher = KEYWORDS_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            IncorrectCommand incorrectCommand = new IncorrectCommand(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
            return new AggregateCommand(incorrectCommand);
        }

        // keywords delimited by whitespace
        final String[] keywords = matcher.group("keywords").split("\\s+");
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        FindCommand findCommand = new FindCommand(keywordSet);
        return new AggregateCommand(findCommand);
    }


}
