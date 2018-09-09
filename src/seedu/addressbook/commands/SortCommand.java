package seedu.addressbook.commands;

public class SortCommand extends Command {
  public static final String COMMAND_WORD = "sort";

  public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the address book by name. ";

  private static final String MESSAGE_SUCCESS = "Address book sorted!";

  @Override
  public CommandResult execute() {
    addressBook.sort();
    return new CommandResult(MESSAGE_SUCCESS);
  }
}
