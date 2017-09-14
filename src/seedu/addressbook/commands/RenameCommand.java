package seedu.addressbook.commands;

import java.util.*;

public class RenameCommand extends Command {
    public static final String COMMAND_WORD = "rename";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Renames a contact in the address book using their index number and the new name.\n"
            + "Example: " + COMMAND_WORD + " 1 john";

    public RenameCommand(int targetVisibleIndex) {
        super(targetVisibleIndex);
    }

 //   @Override
 //   public CommandResult execute() {
  //      return;
   // }
}
