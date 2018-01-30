package seedu.addressbook.commands;

public class AggregateCommand {
    private AddCommand addCommand;
    private ClearCommand clearCommand;
    private DeleteCommand deleteCommand;
    private ExitCommand exitCommand;
    private FindCommand findCommand;
    private HelpCommand helpCommand;
    private IncorrectCommand incorrectCommand;
    private ListCommand listCommand;
    private ViewAllCommand viewAllCommand;
    private ViewCommand viewCommand;

    public AggregateCommand(AddCommand addCommand) {
        this.addCommand = addCommand;
    }

    public AggregateCommand(ClearCommand clearCommand) {
        this.clearCommand = clearCommand;
    }

    public AggregateCommand(DeleteCommand deleteCommand) {
        this.deleteCommand = deleteCommand;
    }

    public AggregateCommand(ExitCommand exitCommand) {
        this.exitCommand = exitCommand;
    }

    public AggregateCommand(FindCommand findCommand) {
        this.findCommand = findCommand;
    }

    public AggregateCommand(HelpCommand helpCommand) {
        this.helpCommand = helpCommand;
    }

    public AggregateCommand(IncorrectCommand incorrectCommand) {
        this.incorrectCommand = incorrectCommand;
    }

    public AggregateCommand(ListCommand listCommand) {
        this.listCommand = listCommand;
    }

    public AggregateCommand(ViewAllCommand viewAllCommand) {
        this.viewAllCommand = viewAllCommand;
    }

    public AggregateCommand(ViewCommand viewCommand) {
        this.viewCommand = viewCommand;
    }
}
