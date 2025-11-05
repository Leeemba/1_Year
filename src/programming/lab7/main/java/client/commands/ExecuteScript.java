package client.commands;

import client.network.UDPClient;
import client.utility.Console;
import common.exceptions.WrongAmountOfArgsException;
import common.network.CommandNames;
import common.utility.ConsoleFormat;

public class ExecuteScript extends Command implements  Executable{

    private Console console;
    private UDPClient client;

    public ExecuteScript(Console console,UDPClient client){
        super(CommandNames.EXECUTE_SCRIPT,CommandNames.Descriptions.DESCR_EXECUTE);
        this.console = console;
        this.client = client;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (args.isBlank()) throw new WrongAmountOfArgsException();
        console.println(ConsoleFormat.coloring("Путь получен успешно!",ConsoleFormat.GREEN,ConsoleFormat.ITALIC));
        console.println(ConsoleFormat.coloring("Выполнение скрипта "+args,ConsoleFormat.GREEN,ConsoleFormat.ITALIC));
    }
}
