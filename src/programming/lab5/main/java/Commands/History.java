package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;
import ConsoleOperations.Hint;
import Exceptions.WrongAmountOfArgsException;
import Managers.CommandManager;

public class History extends Command{
    private final Console console;
    private final CommandManager commandManager;
    private final static String NAME = "history";
    private final static String DESCRIPTION = "выводит последние 9 команд";
    public History(Console console,CommandManager commandManager) {
        super(NAME,DESCRIPTION);
        this.commandManager =commandManager;
        this.console =console;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
    if (!args.isBlank()) throw new WrongAmountOfArgsException();
    var history = commandManager.getCommandHistory();
    if( history.isEmpty()) {
        console.printHint(Hint.HISTORYHINT.toString());
    }else{
        history.stream()
                .skip(Math.max(history.size() - 9, 0))
                .forEach(s -> console.print(ConsoleFormat.coloring(s + "||",ConsoleFormat.GREEN,ConsoleFormat.BOLD)));
    }
    console.println("");
    }
}
