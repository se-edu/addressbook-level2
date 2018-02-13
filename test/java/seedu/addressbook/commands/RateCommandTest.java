package seedu.addressbook.commands;

import org.junit.Test;
import seedu.addressbook.data.AddressBook;
import seedu.addressbook.data.person.*;
import seedu.addressbook.storage.StorageFile;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class RateCommandTest {
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

        RateCommand rc = new RateCommand(lastShownList.size(), 2);
        rc.setData(addressBook, lastShownList);
        rc.execute();
        List<ReadOnlyPerson> finalList = addressBook.getAllPersons().immutableListView();
        assertEquals(2, finalList.get(finalList.size()-1).getRating());
    }
}