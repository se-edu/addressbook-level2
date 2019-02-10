package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.util.TestUtil;


import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class SortCommandTest {
    @Test
    public void sortCommand() throws Exception{
        List<ReadOnlyPerson> EMPTY_PERSON_LIST = Collections.emptyList();
        AddressBook expectedAddressBook;
        AddressBook addressBook;


        Person esther = new Person(new Name("Esther Potato"), new Phone("555555", false),
                new Email("esther@not.a.real.potato", false), new Address("555, epsilon street", false),Collections.EMPTY_SET);
        Person betsy = new Person(new Name("Betsy Choo"), new Phone("222222", false),
                new Email("benchoo@nus.edu.sg", false), new Address("222, beta street", false),Collections.EMPTY_SET);
        Person charlie = new Person(new Name("Charlie Dickson"), new Phone("333333", false),
                new Email("charlie.d@nus.edu.sg", false), new Address("333, gamma street", false),Collections.EMPTY_SET);
        Person adam = new Person(new Name("Adam Brown"), new Phone("111111", false),
                new Email("adam@gmail.com", false), new Address("111, alpha street", false),Collections.EMPTY_SET);
        Person dickson = new Person(new Name("Dickson Ee"), new Phone("444444", false),
                new Email("dickson@nus.edu.sg", false), new Address("444, delta street", false),Collections.EMPTY_SET);

        addressBook = TestUtil.createAddressBook(esther,betsy,charlie,adam,dickson);
        expectedAddressBook = TestUtil.createAddressBook(adam,betsy,charlie,dickson,esther);

        SortCommand command = new SortCommand();
        command.setData(addressBook, EMPTY_PERSON_LIST);
        command.execute();

        assertEquals(addressBook,expectedAddressBook);
    }
}
