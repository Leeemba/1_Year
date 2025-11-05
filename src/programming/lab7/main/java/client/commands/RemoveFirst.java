package client.commands;

import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.ErrorResponseException;
import common.exceptions.ResponseErrorMessageException;
import common.exceptions.WrongAmountOfArgsException;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.RemoveFirstResponse;
import common.utility.ConsoleFormat;

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
            var response = (RemoveFirstResponse) client.sendAndReceiveCommand(new Request(CommandNames.REMOVE_FIRST, SessionHandler.getInstance().getCurrentUser()));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
            console.println(ConsoleFormat.coloring(response.getMessage(),ConsoleFormat.GREEN,ConsoleFormat.ITALIC));
        }catch (ResponseErrorMessageException | ErrorResponseException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
