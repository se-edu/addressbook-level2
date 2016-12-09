package seedu.addressbook.testutil;

import java.util.Arrays;
import java.util.List;

import seedu.addressbook.data.person.Person;

/**
 * A utility class to generate test data.
 */
public class TestDataHelper {
    
    public List<Person> generatePersonList(Person... persons) {
        return Arrays.asList(persons);
    }
    
}
