package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.List;

/**
 * Sort the list of persons
 */

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book sorted by names.\n"
            + "Example: " + COMMAND_WORD;
     @Override
     public CommandResult execute() {
         List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
         List<ReadOnlyPerson> sortedPersons = new ArrayList<ReadOnlyPerson>();
         int i,j;
         for(i=0;i<allPersons.size();i++){
             sortedPersons.add(allPersons.get(i));
             }
         int n=allPersons.size();
         for(i=0;i<n-1;i++){
             for(j=0;j<n-i-1;j++){
                 if (sortedPersons.get(j).getName().toString().toLowerCase().compareTo(sortedPersons.get(j+1).getName().toString().toLowerCase()) > 0) {
                     ReadOnlyPerson temp = sortedPersons.get(j);
                     sortedPersons.set(j, sortedPersons.get(j + 1));
                     sortedPersons.set(j + 1, temp);
                 }
             }
         }

         return new CommandResult(getMessageForPersonSortShownSummary(sortedPersons), sortedPersons);
     }
}