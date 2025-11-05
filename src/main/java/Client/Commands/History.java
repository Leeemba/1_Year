package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.ResponseErrorMessageException;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.HistoryResponse;
import Common.Utility.ConsoleFormat;
import Common.Utility.Hint;

import java.io.IOException;


public class History extends Command {
    private final Console console;

    private final UDPClient client;
    public History(Console console, UDPClient client) {
        super(CommandNames.HISTORY);
        this.client = client;
        this.console =console;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if (!args.isBlank()) throw new WrongAmountOfArgsException();
        try {
            var response = (HistoryResponse)client.sendAndReceiveCommand(new Request(CommandNames.HISTORY));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
            console.println(response.getHistory().toString());
        }catch (ResponseErrorMessageException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}

