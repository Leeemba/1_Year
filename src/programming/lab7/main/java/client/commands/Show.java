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
import common.network.responses.ShowResponse;
import common.utility.ConsoleFormat;

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
            var response = (ShowResponse) client.sendAndReceiveCommand(new Request(CommandNames.SHOW, SessionHandler.getInstance().getCurrentUser()));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
            var coll = response.getResult();
            coll.forEach(organization -> console.println(ConsoleFormat.coloring(organization.toString(),ConsoleFormat.PURPLE,ConsoleFormat.BOLD)));
        }catch (ResponseErrorMessageException | ErrorResponseException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}

