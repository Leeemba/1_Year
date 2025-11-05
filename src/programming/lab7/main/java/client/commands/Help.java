package client.commands;

import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.ErrorResponseException;
import common.exceptions.WrongAmountOfArgsException;
import common.network.CommandNames;
import common.network.requests.Request;
import common.network.responses.HelpResponse;
import common.utility.ConsoleFormat;

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
            var response = (HelpResponse) client.sendAndReceiveCommand(new Request(CommandNames.HELP, SessionHandler.getInstance().getCurrentUser()));
            response.getHelpInfo()
                    .forEach((s, s2) -> console.println(
                            ConsoleFormat.coloring(s+": ",ConsoleFormat.GREEN,ConsoleFormat.ITALIC)
                                    + ConsoleFormat.coloring(s2,ConsoleFormat.BLUE,ConsoleFormat.BOLD)));
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }catch (ErrorResponseException e){
            console.printErr(e.getMessage());
        }
    }
}
