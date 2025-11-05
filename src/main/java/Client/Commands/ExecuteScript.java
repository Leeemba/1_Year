package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Client.utility.RuntimeManager;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.requests.Request;
import Common.Network.responses.Response;
import Common.Utility.ConsoleFormat;

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
