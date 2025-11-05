package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.ResponseErrorMessageException;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.ShowResponse;
import Common.Utility.ConsoleFormat;

import java.io.IOException;


public class Show extends Command {

    private final Console console;
    private final UDPClient client;

    public Show(Console console, UDPClient client) {
        super(CommandNames.SHOW);
        this.client = client;
        this.console =console;
    }


    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        try{
            var response = (ShowResponse) client.sendAndReceiveCommand(new Request(CommandNames.SHOW));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
            var coll = response.getResult();
            coll.forEach(organization -> console.println(ConsoleFormat.coloring(organization.toString(),ConsoleFormat.PURPLE,ConsoleFormat.BOLD)));
        }catch (ResponseErrorMessageException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
