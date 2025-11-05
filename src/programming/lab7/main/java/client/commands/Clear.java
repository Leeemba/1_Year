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
import common.network.responses.ClearResponse;
import common.utility.ConsoleFormat;

import java.io.IOException;

public class Clear extends Command implements Executable {
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
            var response = (ClearResponse) client.sendAndReceiveCommand(new Request(CommandNames.CLEAR, SessionHandler.getInstance().getCurrentUser()));
            if (response.getStatus().equals(ResponseStatus.ERROR)) {
                throw new ResponseErrorMessageException(response.getErrorMessage());
            }
            console.println(ConsoleFormat.coloring(response.getMessage(),ConsoleFormat.GREEN,ConsoleFormat.BOLD));
        }catch (ResponseErrorMessageException | ErrorResponseException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }

}
