package seedu.addressbook.data.person;

import java.util.Arrays;
import java.util.List;

import seedu.addressbook.data.exception.IllegalValueException;

/**
 * Represents a Person's name in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidName(String)}
 */
public class Name {

    public static final String EXAMPLE = "John Doe";
    public static final String MESSAGE_NAME_CONSTRAINTS = "Person names should be spaces or alphabetic characters";
    public static final String NAME_VALIDATION_REGEX = "[\\p{Alpha} ]+";
    public final String fullName;

    /**
     * Validates given name.
     *
     * @throws IllegalValueException if given name string is invalid.
     */
    public Name(String name) throws IllegalValueException {
        String trimmedName = name.trim();
        if (!isValidName(trimmedName)) {
            throw new IllegalValueException(MESSAGE_NAME_CONSTRAINTS);
        }
        this.fullName = trimmedName;
    }

    /**
     * Returns true if the given string is a valid person name.
     */
    public static boolean isValidName(String test) {
        return test.matches(NAME_VALIDATION_REGEX);
    }

    /**
     * Retrieves a listing of every word in the name, in order.
     */
    public List<String> getWordsInName() {
        return Arrays.asList(fullName.split("\\s+"));
    }

    @Override
    public String toString() {
        return fullName;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Name // instanceof handles nulls
                && this.fullName.equals(((Name) other).fullName)); // state check
    }

    @Override
    public int hashCode() {
        return fullName.hashCode();
    }

    /**
      * Returns true if the other name is very similar to this name.
      * Two names are considered similar if ...
      */
    public boolean isSimilar(Name other) {
        if (other == null) {
            return false;
        }

        // different case
        // different order
        // superset
        // subset
            
        List<String> tokensOriginal = this.getWordsInName();
        List<String> tokensOther = this.getWordsInName();

        // First lowercase every token
        
        tokensOriginal.replaceAll(String::toLowerCase);
        tokensOther.replaceAll(String::toLowerCase);

        int matchedOriginal = 0;
        int matchedOther = 0;


        for (int i = 0; i < tokensOriginal.size(); i++) {
            if (tokensOther.contains(tokensOriginal.get(i))) {
                ++matchedOriginal;
            }
        }

        for (int i = 0; i < tokensOther.size(); i++) {
            if (tokensOriginal.contains(tokensOther.get(i))) {
                ++matchedOther;
            }
        }
        
        double matched = ((matchedOriginal / tokensOriginal.size()) 
                + (matchedOther / tokensOther.size())) / 2.0;

        if (matched >= 0.4)
            return true;
        else
            return false;
    }

}
