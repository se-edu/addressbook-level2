package seedu.addressbook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        // read from address book
        FIND("find"),
        LIST("list"),
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

    public static void main(String... a) {
        java.util.Scanner sc = new java.util.Scanner(System.in);
        Parser p = new Parser();
        while (true) {
            try {
                String full = sc.nextLine();
                System.out.println();
                System.out.println("\tCOMMAND: " + p.identifyCommand(full));
                String args = p.extractArguments(full);
                System.out.println("\tFULL_ARGS: " + args);
                System.out.println("\tNAME: " + p.extractNameFromAddCommandArgs(args));
                System.out.println("\tPHONE: " + (p.isPhonePrivateFromAddCommandArgs(args) ? "(private) " : "") + p.extractPhoneFromAddCommandArgs(args));
                System.out.println("\tEMAIL: " + (p.isEmailPrivateFromAddCommandArgs(args) ? "(private) " : "") + p.extractEmailFromAddCommandArgs(args));
                System.out.println("\tADDRESS: " + (p.isAddressPrivateFromAddCommandArgs(args) ? "(private) " : "") + p.extractAddressFromAddCommandArgs(args));
                System.out.println("\tTAGS: " + Arrays.toString(p.extractTagsFromAddCommandArgs(args).toArray()));
                System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Parser() {}

    public static final String COMMENT_LINE_FORMAT_REGEX = "#.*";

    public static final Pattern BASIC_COMMAND_FORMAT =
            Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    public static final Pattern ADD_COMMAND_ARGS_FORMAT = // '/' forward slashes are reserved
            Pattern.compile(
                    "(?<name>[^/]+)"
                    + " (?<isPhonePrivate>p?)p/(?<phone>[^/]+)"
                    + " (?<isEmailPrivate>p?)e/(?<email>[^/]+)"
                    + " (?<isAddressPrivate>p?)a/(?<address>[^/]+)"
                    + "(?<tagArguments>(?: t/[^/]+)*)" // variable number of tags
            );

    /*
     * ===========================================
     *         PRE-PROCESSING AND CHECKS
     * ===========================================
     */

    /**
     * Checks if the user input line should be ignored.
     * Input should be ignored if it is parsed as a comment, is only whitespace, or is empty.
     *
     * @param rawInputLine full raw user input line.
     * @return true if the entire user input line should be ignored.
     */
    public boolean shouldIgnore(String rawInputLine) {
        return rawInputLine.trim().isEmpty() || isCommentLine(rawInputLine);
    }

    /**
     * Checks if the user input line is a comment line.
     *
     * @param rawInputLine full raw user input line.
     * @return true if input line is a comment.
     */
    public boolean isCommentLine(String rawInputLine) {
        return rawInputLine.trim().matches(COMMENT_LINE_FORMAT_REGEX);
    }

    /**
     * Identifies the intended command type of the user input string.
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
     *          ADD COMMAND ARG PARSING
     * ===========================================
     */

    /**
     * Extracts the string representing the new person's name from the arguments to the add command.
     *
     * @param addArguments full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return the part of the arguments string representing the name
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public String extractNameFromAddCommandArgs(String addArguments) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("name");
    }

    /**
     * Extracts the string representing the new person's phone from the arguments to the add command.
     *
     * @param addArguments full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return the part of the arguments string representing the phone
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public String extractPhoneFromAddCommandArgs(String addArguments) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("phone");
    }

    /**
     * Checks whether the new person's phone is private from the arguments to the add command.
     *
     * @param addArguments full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return true if the arguments string specifies that the person's phone should be marked private
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public boolean isPhonePrivateFromAddCommandArgs(String addArguments) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return !matcher.group("isPhonePrivate").isEmpty();
    }

    /**
     * Extracts the string representing the new person's email from the arguments to the add command.
     *
     * @param addArguments full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return the part of the arguments string representing the email
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public String extractEmailFromAddCommandArgs(String addArguments) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("email");
    }

    /**
     * Checks whether the new person's email is private from the arguments to the add command.
     *
     * @param addArguments full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return true if the arguments string specifies that the person's email should be marked private
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public boolean isEmailPrivateFromAddCommandArgs(String addArguments) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return !matcher.group("isEmailPrivate").isEmpty();
    }

    /**
     * Extracts the string representing the new person's address from the arguments to the add command.
     *
     * @param addArguments full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return the part of the arguments string representing the address
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public String extractAddressFromAddCommandArgs(String addArguments) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return matcher.group("address");
    }

    /**
     * Checks whether the new person's address is private from the arguments to the add command.
     *
     * @param addArguments full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return true if the arguments string specifies that the person's address should be marked private
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public boolean isAddressPrivateFromAddCommandArgs(String addArguments) throws InvalidCommandFormatException {
        final Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        return !matcher.group("isAddressPrivate").isEmpty();
    }

    /**
     * Extracts the strings representing the new person's tags from the arguments to the add command.
     *
     * @param addArguments full add command arguments string as extracted from {@link #extractArguments(String)}
     * @return list of all substrings representing tags from the arguments
     * @throws InvalidCommandFormatException if the arguments string is invalid
     */
    public List<String> extractTagsFromAddCommandArgs(String addArguments) throws InvalidCommandFormatException {
        Matcher matcher = ADD_COMMAND_ARGS_FORMAT.matcher(addArguments.trim());
        if (!matcher.matches()) {
            throw new InvalidCommandFormatException();
        }
        // pull out tag arguments string for parsing
        final String tagArguments = matcher.group("tagArguments");
        // replace first delimiter prefix, then split
        return Arrays.asList(tagArguments.replaceFirst(" t/", "").split(" t/"));
    }
}
