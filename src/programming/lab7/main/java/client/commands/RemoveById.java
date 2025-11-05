package client.commands;


import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.ErrorResponseException;
import common.exceptions.ResponseErrorMessageException;
import common.exceptions.WrongAmountOfArgsException;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.RemoveByIdRequest;
import common.network.responses.RemoveByIdResponse;
import common.utility.ConsoleFormat;

import java.io.IOException;

public class RemoveById extends  Command{
    private final UDPClient client;
    private final Console console;

    public RemoveById(Console console, UDPClient client) {
        super(CommandNames.REMOVE_BY_ID);
        this.client = client;
        this.console =console;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException{
        if (args.isBlank()) throw new WrongAmountOfArgsException();
        try {
            int id = Integer.parseInt(args);
            var response = (RemoveByIdResponse) client.sendAndReceiveCommand(new RemoveByIdRequest(id, SessionHandler.getInstance().getCurrentUser()));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw  new ResponseErrorMessageException(response.getErrorMessage());
            console.println(ConsoleFormat.coloring(response.getMessage(),ConsoleFormat.GREEN,ConsoleFormat.ITALIC));

        }catch (NumberFormatException e){
            console.printErr("Id должно быть числом типа int!");
        }catch (ResponseErrorMessageException | ErrorResponseException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}

