package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.List;

public class FilterCommand extends Command {
  public static final String COMMAND_WORD = "filter";
  public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all person whose names begin with the specified alphabet.\n"
          + "Example: " + COMMAND_WORD + " A";

  private final char letter;

  public FilterCommand(char letter) {
    this.letter = letter;
  }

  @Override
  public CommandResult execute() {
    final List<ReadOnlyPerson> personsFound = getPersonsWithNameStartingWithLetter(letter);
    return new CommandResult(getMessageForPersonListShownSummary(personsFound), personsFound);
  }

  private List<ReadOnlyPerson> getPersonsWithNameStartingWithLetter(char letter) {
    final List<ReadOnlyPerson> result = new ArrayList<>();
    for(ReadOnlyPerson person: addressBook.getAllPersons()) {
      if(person.getName().toString().charAt(0) == letter) {
        result.add(person);
      }
    }
    return result;
  }
}
