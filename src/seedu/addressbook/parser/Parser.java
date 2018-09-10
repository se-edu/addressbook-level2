package seedu.addressbook.parser;

import static seedu.addressbook.common.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.addressbook.common.Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

import seedu.addressbook.commands.AddCommand;
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

    public static final String INVALID_ITEM = "Invalid Item";
    private static boolean isPhonePrivate;
    private static boolean isEmailPrivate;
    private static boolean isAddressPrivate;

    /**
     * Signals that the user input could not be parsed.
     */
    public static class ParseException extends Exception {
        ParseException(String message) {
            super(message);
        }
    }
    
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     */
    public Command parseCommand(String userInput) {
        StringTokenizer st = new StringTokenizer(userInput.trim());

        //Retrieve command word
        String commandWord;
        if (st.hasMoreElements()) {
            commandWord = st.nextToken();
        } else {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        //Retrieve arguments
        StringBuilder sb = new StringBuilder();
        while (st.hasMoreElements()) {
            sb.append(st.nextToken() + " ");
        }
        String arguments = sb.toString();

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return prepareAdd(arguments);

        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return prepareFind(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ViewCommand.COMMAND_WORD:
            return prepareView(arguments);

        case ViewAllCommand.COMMAND_WORD:
            return prepareViewAll(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD: // Fallthrough
        default:
            return new HelpCommand();
        }
    }

    /**
     * Parses arguments in the context of the add person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareAdd(String args) {
        String[] tokenList = args.trim().split(" ");

        //Extract argument items
        String name = extractName(tokenList);
        String phone = extractPhone(tokenList);
        String email = extractEmail(tokenList);
        String address = extractAddress(tokenList);
        String tags = extractTags(tokenList);

        //Check if any of the argument item is invalid
        if (name.equals(INVALID_ITEM) || phone.equals(INVALID_ITEM) || email.equals(INVALID_ITEM) || address.equals(INVALID_ITEM)) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE));
        }

        try {
            return new AddCommand(
                    name,

                    phone,
                    getPhonePrivate(),

                    email,
                    getEmailPrivate(),

                    address,
                    getAddressPrivate(),

                    getTagsFromArgs(tags)
            );
        } catch (IllegalValueException ive) {
            return new IncorrectCommand(ive.getMessage());
        }
    }

    /**
     * Extracts name from argument.
     * @param tokenList
     * @return valid name
     */
    private static String extractName(String[] tokenList) {
        StringBuilder sb = new StringBuilder();
        for (String token : tokenList) {
            if (!token.contains("/") && !token.isEmpty()) {
                sb.append(token + " ");
            } else {
                break;
            }
        }
        String name = sb.toString();

        if (!checkValidity(name)) {
            name = INVALID_ITEM;
        }

        return name.trim();
    }

    /**
     * Extract phone number from argument.
     * @param tokenList
     * @return valid phone number
     */
    private String extractPhone(String[] tokenList) {
        String phone = "";
        int numOccurrence = 0;
        for (String token : tokenList) {
            if (token.contains("pp/")) {
                String[] subTokenList = token.split("/");
                phone = subTokenList[1];
                numOccurrence++;
                setPhonePrivate(true);
            } else if (token.contains("p/")) {
                String[] subTokenList = token.split("/");
                phone = subTokenList[1];
                numOccurrence++;
                setPhonePrivate(false);
            } else {
                continue;
            }
        }

        if(!checkValidity(phone) || numOccurrence > 1) {
            phone = INVALID_ITEM;
        }

        return phone.trim();
    }

    /**
     * Extract email address from argument.
     * @param tokenList
     * @return valid email address
     */
    private String extractEmail(String[] tokenList) {
        String email = "";
        int numOccurrence = 0;
        for (String token : tokenList) {
            if (token.contains("pe/")) {
                String[] subTokenList = token.split("/");
                email = subTokenList[1];
                numOccurrence++;
                setEmailPrivate(true);
            } else if (token.contains("e/")) {
                String[] subTokenList = token.split("/");
                email = subTokenList[1];
                numOccurrence++;
                setEmailPrivate(false);
            } else {
                continue;
            }
        }

        if(!checkValidity(email) || numOccurrence > 1) {
            email = INVALID_ITEM;
        }

        return email.trim();
    }

    /**
     * Extract address.
     * @param tokenList
     * @return valid address
     */
    private String extractAddress(String[] tokenList) {
        StringBuilder sb = new StringBuilder();
        boolean addressFound =  false;
        for (String token : tokenList) {
            if (token.contains("pa/")) {
                String[] subTokenList = token.split("/");
                sb.append(subTokenList[1] + " ");
                setAddressPrivate(true);
                addressFound = true;
            } else if (token.contains("a/")) {
                String[] subTokenList = token.split("/");
                sb.append(subTokenList[1] + " ");
                setAddressPrivate(false);
                addressFound = true;
            } else if (addressFound && checkValidity(token)) {
                sb.append(token + " ");
            } else {
                continue;
            }
        }

        String address = sb.toString();

        if(!checkValidity(address)) {
            address = INVALID_ITEM;
        }

        return address.trim();
    }

    /**
     * Extract tags.
     * @param tokenList
     * @return valid tag
     */
    private String extractTags(String[] tokenList) {
        StringBuilder sb = new StringBuilder();
        boolean tokenFound = false;
        for (String token : tokenList) {
            if (token.contains("t/")) {
                sb.append(token + " ");
                tokenFound = true;
            } else if (tokenFound && !token.contains("t/")) {
                sb.append(token + " ");
            } else {
                continue;
            }
        }

        String tags = sb.toString();

        return tags.trim();
    }

    public boolean getPhonePrivate() {
        return this.isPhonePrivate;
    }

    public void setPhonePrivate(boolean val) {
        this.isPhonePrivate = val;
    }

    public boolean getEmailPrivate() {
        return this.isEmailPrivate;
    }

    public void setEmailPrivate(boolean val) {
        this.isEmailPrivate = val;
    }

    public boolean getAddressPrivate() {
        return this.isAddressPrivate;
    }

    public void setAddressPrivate(boolean val) {
        this.isAddressPrivate = val;
    }

    /**
     * Checks the validity of an argument item.
     * @param item
     * @return true if item is valid, false otherwise.
     */
    private static boolean checkValidity (String item) {
        if (item.isEmpty() || item.contains("/")) {
            return false;
        }
        return true;
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
        final Collection<String> tagStrings = Arrays.asList(tagArguments.replaceFirst("t/", "").split(" t/"));
        return new HashSet<>(tagStrings);
    }


    /**
     * Parses arguments in the context of the delete person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareDelete(String args) {
        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new DeleteCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the view command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareView(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new ViewCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }
    }

    /**
     * Parses arguments in the context of the view all command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareViewAll(String args) {

        try {
            final int targetIndex = parseArgsAsDisplayedIndex(args);
            return new ViewAllCommand(targetIndex);
        } catch (ParseException pe) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewAllCommand.MESSAGE_USAGE));
        } catch (NumberFormatException nfe) {
            return new IncorrectCommand(MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
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
        if (args.trim().isEmpty()) {
            throw new ParseException("Could not find index number to parse");
        } else {
            return Integer.parseInt(args.trim());
        }
    }


    /**
     * Parses arguments in the context of the find person command.
     *
     * @param args full command args string
     * @return the prepared command
     */
    private Command prepareFind(String args) {
        if (args.trim().isEmpty()) {
            return new IncorrectCommand(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        // keywords delimited by whitespace
        final String[] keywords = args.trim().split(" ");;
        final Set<String> keywordSet = new HashSet<>(Arrays.asList(keywords));
        return new FindCommand(keywordSet);
    }


}
