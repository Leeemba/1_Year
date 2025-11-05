package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.ExitPoint;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.requests.Request;
import Common.Network.responses.HelpResponse;
import Common.Utility.ConsoleFormat;

import java.io.IOException;

public class Help extends Command{
    private Console console;
    private UDPClient client;

    public Help(Console console, UDPClient client) {
        super(CommandNames.HELP);
        this.console = console;
        this.client = client;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if(!args.isBlank()) throw  new WrongAmountOfArgsException();
        try {
            var response = (HelpResponse) client.sendAndReceiveCommand(new Request(CommandNames.HELP));
            response.getHelpInfo()
                .forEach((s, s2) -> console.println(
                        ConsoleFormat.coloring(s+": ",ConsoleFormat.GREEN,ConsoleFormat.ITALIC)
                                + ConsoleFormat.coloring(s2,ConsoleFormat.BLUE,ConsoleFormat.BOLD)));
    }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
