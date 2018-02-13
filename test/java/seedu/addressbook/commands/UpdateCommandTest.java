package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.Main;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;
import seedu.addressbook.storage.StorageFile;

import java.util.*;

import static org.junit.Assert.*;

public class UpdateCommandTest {

    @Test
    public void execute() throws Exception {
        StorageFile sf = new StorageFile("test.xml");
        AddressBook addressBook = sf.load();
        List<? extends ReadOnlyPerson> lastShownList = Collections.emptyList();

        AddCommand ac = new AddCommand(Name.EXAMPLE, Phone.EXAMPLE, false, Email.EXAMPLE, false, Address.EXAMPLE,
                false, Collections.emptySet());
        ac.setData(addressBook, lastShownList);
        ac.execute();
        sf.save(addressBook);

        ListCommand lc = new ListCommand();
        lc.setData(addressBook, lastShownList);
        lastShownList = lc.execute().getRelevantPersons().get();
        sf.save(addressBook);

        UpdateCommand uc = new UpdateCommand(lastShownList.size()+"",
                "abc",
                Phone.EXAMPLE, false,
                Email.EXAMPLE, false,
                Address.EXAMPLE, false,
                Collections.emptySet());
        uc.setData(addressBook, lastShownList);
        uc.execute();
        List<ReadOnlyPerson> finalList = addressBook.getAllPersons().immutableListView();
        assertEquals("abc", finalList.get(finalList.size()-1).getName().toString());
    }
}