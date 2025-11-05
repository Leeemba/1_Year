package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.ResponseErrorMessageException;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.RemoveFirstResponse;
import Common.Utility.ConsoleFormat;

import java.io.IOException;


public class RemoveFirst extends  Command{
    private final UDPClient client;
    private final Console console;

    public RemoveFirst(Console console, UDPClient client) {
        super(CommandNames.REMOVE_FIRST);
        this.client =client;
        this.console=console;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        try {
            var response = (RemoveFirstResponse) client.sendAndReceiveCommand(new Request(CommandNames.REMOVE_FIRST));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getMessage());
            console.println(ConsoleFormat.coloring(response.getMessage(),ConsoleFormat.GREEN,ConsoleFormat.ITALIC));
        }catch (ResponseErrorMessageException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
