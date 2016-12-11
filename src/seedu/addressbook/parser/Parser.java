package seedu.addressbook.parser;

import seedu.addressbook.commands.*;
import seedu.addressbook.data.exception.IllegalValueException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.addressbook.common.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

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
    public String[] parseCommand(String userInput) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            return new String[] {IncorrectCommand.class.getSimpleName().toLowerCase(), null};
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

            case AddCommand.COMMAND_WORD:
                return new String[] {AddCommand.class.getSimpleName().replace("Command", "").toLowerCase(), arguments};

            case DeleteCommand.COMMAND_WORD:
                return new String[] {DeleteCommand.class.getSimpleName().replace("Command", "").toLowerCase(), arguments};

            case ClearCommand.COMMAND_WORD:
                return new String[] {ClearCommand.class.getSimpleName().replace("Command", "").toLowerCase(), null};

            case FindCommand.COMMAND_WORD:
                return new String[] {FindCommand.class.getSimpleName().replace("Command", "").toLowerCase(), arguments};

            case ListCommand.COMMAND_WORD:
                return new String[] {ListCommand.class.getSimpleName().replace("Command", "").toLowerCase(), null};

            case ViewCommand.COMMAND_WORD:
                return new String[] {ViewCommand.class.getSimpleName().replace("Command", "").toLowerCase(), arguments};

            case ViewAllCommand.COMMAND_WORD:
                return new String[] {ViewAllCommand.class.getSimpleName().replace("Command", "").toLowerCase(), arguments};

            case ExitCommand.COMMAND_WORD:
                return new String[] {ExitCommand.class.getSimpleName().replace("Command", "").toLowerCase(), null};

            case HelpCommand.COMMAND_WORD:
            default:
                return new String[] {HelpCommand.class.getSimpleName().replace("Command", "").toLowerCase(), null};
        }
    }

    /**
     * Checks whether the private prefix of a contact detail in the add command's arguments string is present.
     */
    public static boolean isPrivatePrefixPresent(String matchedPrefix) {
        return matchedPrefix.equals("p");
    }

    /**
     * Extracts the new person's tags from the add command's tag arguments string.
     * Merges duplicate tag strings.
     */
    public static Set<String> getTagsFromArgs(String tagArguments) throws IllegalValueException {
        // no tags
        if (tagArguments.isEmpty()) {
            return Collections.emptySet();
        }
        // replace first delimiter prefix, then split
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
        return new HashSet<>(tagStrings);
    }

    /**
     * Parses the given arguments string as a single index number.
     *
     * @param args arguments string to parse as index number
     * @return the parsed index number
     * @throws ParseException if no region of the args string could be found for the index
     * @throws NumberFormatException the args string region is not a valid number
     */
    public int parseArgsAsDisplayedIndex(String args) throws ParseException, NumberFormatException {
        final Matcher matcher = PERSON_INDEX_ARGS_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException("Could not find index number to parse");
        }
        return Integer.parseInt(matcher.group("targetIndex"));
    }

}