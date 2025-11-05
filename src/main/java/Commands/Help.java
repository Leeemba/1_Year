package Commands;

import ConsoleOperations.Console;
import ConsoleOperations.ConsoleFormat;

import Managers.CommandManager;

public class Help extends Command implements Executable{

    private final CommandManager commandManager;
    private final Console console;
    private final static String NAME = "help";
    private final static String DESCRIPTION = "выводит справку по доступным командам";

    public Help(Console console,CommandManager commandManager ){
        super(NAME,DESCRIPTION);
        this.commandManager = commandManager;
        this.console =console;
    }







    @Override
    public void execute(String args){
        this.commandManager.getCommands()
                .forEach((command -> console.println
                        (ConsoleFormat.coloring(command.getName()+": ",ConsoleFormat.GREEN,ConsoleFormat.ITALIC)
                                + ConsoleFormat.coloring(command.getDescription(),ConsoleFormat.BLUE,ConsoleFormat.BOLD))));

    }



}
