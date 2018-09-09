package seedu.addressbook.commands;

public class SwapCommand extends Command{
    public static final String COMMAND_WORD = "swap";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Swap the positions of two persons identified by indices in address book.\n"
            + "Parameters: INDEX,INDEX\n"
            + "Example: " + COMMAND_WORD + " 1,2";

    public static final String MESSAGE_VIEW_PERSON_DETAILS = "Swap successfully.";

    public static int targetIndex1;
    public static int targetIndex2;

    public SwapCommand(int index1, int index2){
        setTargetIndex1(index1);
        setTargetIndex2(index2);
    }
    

    /**
     * Set the targetIndex2 to index2.
     *
     * @param index2 the corresponding index2
     * */
    private void setTargetIndex2(int index2) {
        this.targetIndex2 = index2;
    }

    /**
     * Set the targetIndex1 to index1.
     *
     * @param index1 the corresponding index1
     * */
    private void setTargetIndex1(int index1) {
        this.targetIndex1 = index1;
    }


}
