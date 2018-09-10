package seedu.addressbook.commands;

        import static org.junit.Assert.assertEquals;

        import java.util.Arrays;
        import java.util.Collections;
        import java.util.HashSet;
        import java.util.List;
        import java.util.Set;

        import org.junit.Test;

        import seedu.addressbook.data.AddressBook;
        import seedu.addressbook.data.exception.IllegalValueException;
        import seedu.addressbook.data.person.ReadOnlyPerson;
        import seedu.addressbook.data.person.Person;
        import seedu.addressbook.data.person.Name;
        import seedu.addressbook.data.person.Phone;
        import seedu.addressbook.data.person.Email;
        import seedu.addressbook.data.person.Address;
        import seedu.addressbook.util.TypicalPersons;

public class PhonePrefixCommandTest {

    private final AddressBook addressBook = new TypicalPersons().getTypicalAddressBook();
    private final TypicalPersons td = new TypicalPersons();

    @Test
    public void execute() throws IllegalValueException {
        //extra Person to match some test cases not covered by existing sample Persons
        Person eliza = new Person(new Name("Eliza Cassan"), new Phone("91122111", false), new Email("ec@gmail.com", false),
                new Address("451 Clementi Road", false), Collections.emptySet());
        addressBook.addPerson(eliza);

        //full matching phone number: matched
        assertPhonePrefixCommandBehavior("91119111", Arrays.asList(td.amy));

        //prefix not substring of any number: not matched
        assertPhonePrefixCommandBehavior("4444", Collections.emptyList());

        //prefix of single phone number: matched
        assertPhonePrefixCommandBehavior("92", Arrays.asList(td.bill));

        //prefix matching multiple phone numbers: matched
        assertPhonePrefixCommandBehavior("91", Arrays.asList(td.amy, eliza));

        //non-prefix substring of phone numbers: not matched
        assertPhonePrefixCommandBehavior("111", Collections.emptyList());

        //prefix matching number in address: not matched
        assertPhonePrefixCommandBehavior("451", Collections.emptyList());
    }

    /**
     * Executes the PhonePrefix command for the given keywords and verifies
     * the result matches the persons in the expectedPersonList exactly.
     */
    private void assertPhonePrefixCommandBehavior(String prefix, List<ReadOnlyPerson> expectedPersonList) {
        PhonePrefixCommand command = createPhonePrefixCommand(prefix);
        CommandResult result = command.execute();

        assertEquals(Command.getMessageForPersonListShownSummary(expectedPersonList), result.feedbackToUser);
    }

    private PhonePrefixCommand createPhonePrefixCommand(String prefix) {
        PhonePrefixCommand command = new PhonePrefixCommand(prefix);
        command.setData(addressBook, Collections.emptyList());
        return command;
    }

}
