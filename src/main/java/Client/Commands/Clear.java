package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.ResponseErrorMessageException;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.ClearResponse;
import Common.Utility.ConsoleFormat;

import java.io.IOException;

public class Clear extends Command implements Executable{
    private Console console;
    private UDPClient client;
    public Clear(Console console,UDPClient client){
        super(CommandNames.CLEAR);
        this.console = console;
        this.client =client;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        try {
            var response = (ClearResponse) client.sendAndReceiveCommand(new Request(CommandNames.CLEAR));
            if (response.getStatus().equals(ResponseStatus.ERROR)) {
                throw new ResponseErrorMessageException(response.getErrorMessage());
            }
            console.println(ConsoleFormat.coloring(response.getMessage(),ConsoleFormat.GREEN,ConsoleFormat.BOLD));
        }catch (ResponseErrorMessageException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }

}
