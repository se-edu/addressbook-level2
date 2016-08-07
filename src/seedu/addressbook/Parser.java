package seedu.addressbook;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses raw user input strings for command execution.
 * Allows modular extraction of meaningful parts of the input string based on context.
 *
 * Instances are safe for use by multiple threads because there is no persisting state.
 */
public class Parser {

    public static class InvalidCommandFormatException extends Exception {}

    public enum Command {
        // changes address book
        ADD("add"),
        DELETE("delete"),
        CLEAR("clear"),
        // updates view with multiple people
        FIND("find"),
        LIST("list"),
        // read single person
        VIEW("view"),
        VIEW_ALL("viewall"),
        // misc
        HELP("help"),
        EXIT("exit");

        public final String commandWord;
        Command(String commandWord) {
            this.commandWord = commandWord;
        }
    }

    public Parser() {}

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_COMMAND_ARGS_FORMAT = // '/' forward slashes are reserved for delimiter prefixes
            Pattern.compile(
                    "(?<name>[^/]+)"
                    + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)"
                    + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)"
                    + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)" // variable number of tags
            );

    // Same as VIEW and VIEW_ALL commands
    public static final Pattern DELETE_COMMAND_ARGS_FORMAT =
            Pattern.compile("(?<targetIndex>.+)");

    public static final Pattern FIND_COMMAND_ARGS_FORMAT =
            Pattern.compile("(?<keywords>\\S+(?:\\s+\\S+)*)"); // one or more keywords separated by whitespace

    /*
     * ===========================================
     *         PRE-PROCESSING AND CHECKS
     * ===========================================
     */

    /**
     * Identifies the intended command type of the user input string. Case-sensitive.
     *
     * @param rawInput full raw input from the user
     * @return the identity of the command
     * @throws InvalidCommandFormatException if unable to parse command identity
     * @see #extractArguments(String)
     */
    public Command identifyCommand(String rawInput) throws InvalidCommandFormatException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(rawInput.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        final String commandWord = matcher.group("commandWord");
        for (Command command : Command.values()) {
            if (command.commandWord.equals(commandWord)) {
                return command;
            }
        }
        throw new InvalidCommandFormatException(); // means that the command word doesn't match any
    }

    /**
     * Extracts the command arguments substring from the user input string.
     *
     * @param rawInput full raw input from the user
     * @return the substring representing the arguments to the command
     * @throws InvalidCommandFormatException if the input does not match the basic command format
     * @see #identifyCommand(String)
     */
    public String extractArguments(String rawInput) throws InvalidCommandFormatException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(rawInput);
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("arguments");
    }

    /*
     * ===========================================
     *                ADD COMMAND
     * ===========================================
     */

    /**
     * Extracts the string representing the new person's name from the arguments to the add command.
     *
     * @param addArgs full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return the part of the arguments string representing the name
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public String extractNameFromAddCommandArgs(String addArgs) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("name");
    }

    /**
     * Extracts the string representing the new person's phone from the arguments to the add command.
     *
     * @param addArgs full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return the part of the arguments string representing the phone
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public String extractPhoneFromAddCommandArgs(String addArgs) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("phone");
    }

    /**
     * Checks whether the new person's phone is private from the arguments to the add command.
     *
     * @param addArgs full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return true if the arguments string specifies that the person's phone should be marked private
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public boolean isPhonePrivateFromAddCommandArgs(String addArgs) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return !matcher.group("isPhonePrivate").isEmpty();
    }

    /**
     * Extracts the string representing the new person's email from the arguments to the add command.
     *
     * @param addArgs full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return the part of the arguments string representing the email
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public String extractEmailFromAddCommandArgs(String addArgs) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("email");
    }

    /**
     * Checks whether the new person's email is private from the arguments to the add command.
     *
     * @param addArgs full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return true if the arguments string specifies that the person's email should be marked private
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public boolean isEmailPrivateFromAddCommandArgs(String addArgs) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return !matcher.group("isEmailPrivate").isEmpty();
    }

    /**
     * Extracts the string representing the new person's address from the arguments to the add command.
     *
     * @param addArgs full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return the part of the arguments string representing the address
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public String extractAddressFromAddCommandArgs(String addArgs) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("address");
    }

    /**
     * Checks whether the new person's address is private from the arguments to the add command.
     *
     * @param addArgs full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return true if the arguments string specifies that the person's address should be marked private
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public boolean isAddressPrivateFromAddCommandArgs(String addArgs) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return !matcher.group("isAddressPrivate").isEmpty();
    }

    /**
     * Extracts the strings representing the new person's tags from the arguments to the add command.
     *
     * @param addArgs full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return list of all substrings representing tags from the arguments
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public List<String> extractTagsFromAddCommandArgs(String addArgs) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        // pull out tag arguments string for parsing
        final String tagArguments = matcher.group("tagArguments");
        if (tagArguments.isEmpty()) {
            return Collections.emptyList();
        }
        // replace first delimiter prefix, then split
        return Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
    }
    
    /*
     * ===========================================
     *          SELECT PERSON COMMANDS
     * ===========================================
     */

    /**
     * Extracts the index number of the delete command's target from it's arguments.
     *
     * @param deleteArgs full delete command arguments string as extracted from {@link #extractArguments(String)}
     * @return the index number of the target for deletion
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public int extractIndexFromDeleteCommandArgs(String deleteArgs) throws InvalidCommandFormatException {
        final Matcher matcher = DELETE_COMMAND_ARGS_FORMAT.matcher(deleteArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        try {
            return Integer.parseInt(matcher.group("targetIndex"));
        } catch (NumberFormatException nfe) {
            throw new InvalidCommandFormatException();
        }
    }

    /**
     * Extracts the index number of the view command's target from it's arguments.
     *
     * @param viewArgs full view command arguments string as extracted from {@link #extractArguments(String)}
     * @return the index number of the target for viewing
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public int extractIndexFromViewCommandArgs(String viewArgs) throws InvalidCommandFormatException {
        return extractIndexFromDeleteCommandArgs(viewArgs); // same args format as delete
    }

    /**
     * Extracts the index number of the view all command's target from it's arguments.
     *
     * @param viewAllArgs full view all command arguments string as extracted from {@link #extractArguments(String)}
     * @return the index number of the target for full viewing
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public int extractIndexFromViewAllCommandArgs(String viewAllArgs) throws InvalidCommandFormatException {
        return extractIndexFromDeleteCommandArgs(viewAllArgs); // same args format as delete
    }

    /*
     * ===========================================
     *              FIND COMMAND
     * ===========================================
     */

    /**
     * Extracts all keywords for the find command from it's arguments.
     *
     * @param findArgs full find command arguments string as extracted from {@link #extractArguments(String)}
     * @return list of all search keywords specified in the arguments
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public Set<String> extractKeywordsFromFindCommandArgs(String findArgs) throws InvalidCommandFormatException {
        final Matcher matcher = FIND_COMMAND_ARGS_FORMAT.matcher(findArgs.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        final Collection<String> keywords = Arrays.asList(matcher.group("keywords").split("\\s+"));
        return new HashSet<>(keywords);
    }

}
