package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.lang.String;

public class SortCommand extends Command{

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays all persons in the address book as a list"
            + "sorted by name.\n"
            + "Example: " + COMMAND_WORD;

    /**
     * returns a list of all persons in the address book in sorted order.
     */
    public List<ReadOnlyPerson> sort( List<ReadOnlyPerson> personList) {
        List<ReadOnlyPerson> newList = new ArrayList<ReadOnlyPerson>();
        for(int i=0; i < personList.size(); i++) {
            newList.add(personList.get(i));
        }
        return divide(newList);
    }

    public List<ReadOnlyPerson> divide(List<ReadOnlyPerson> resultList) {
        if (resultList.size() < 2){
            return resultList;
        }
        int mid = resultList.size()/2;
        List<ReadOnlyPerson> left = new ArrayList<ReadOnlyPerson>(resultList.subList(0, mid));
        List<ReadOnlyPerson> right = new ArrayList<ReadOnlyPerson>(resultList.subList(mid, resultList.size()));

        left = divide(left);
        right = divide(right);
        merge(left, right, resultList);

        return resultList;
    }

    public void merge( List<ReadOnlyPerson> left, List<ReadOnlyPerson> right, List<ReadOnlyPerson> result) {
        int leftIndex = 0;
        int rightIndex = 0;
        int resultIndex = 0;
        while (leftIndex < left.size() && rightIndex < right.size()) {

            boolean isLeftFirst = left.get(leftIndex).getName().toString().compareToIgnoreCase(right.get(rightIndex).getName().toString()) < 0;

            if ( isLeftFirst) {
                result.set(resultIndex, left.get(leftIndex));
                resultIndex++;
                leftIndex++;
            } else {
                result.set(resultIndex, right.get(rightIndex));
                resultIndex++;
                rightIndex++;
            }
        }
        while (leftIndex < left.size()) {
            result.set(resultIndex, left.get(leftIndex));
            resultIndex++;
            leftIndex++;
        }
        while (rightIndex < right.size()) {
            result.set(resultIndex, right.get(rightIndex));
            resultIndex++;
            rightIndex++;
        }
    }


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().immutableListView();
        List<ReadOnlyPerson> allPersonsSorted = sort(allPersons);
        return new CommandResult(getMessageForPersonListShownSummary(allPersonsSorted), allPersonsSorted);
    }

}
