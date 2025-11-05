package client.commands;

import client.network.UDPClient;
import client.utility.Console;
import common.exceptions.*;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.HeadResponse;

import java.io.IOException;

import client.utility.SessionHandler;
import common.exceptions.ResponseErrorMessageException;
import common.exceptions.WrongAmountOfArgsException;


public class Head extends Command{
    private final Console console;
    private UDPClient client;

    public Head(Console console, UDPClient client) {
        super(CommandNames.HEAD);
        this.client = client;
        this.console = console;
    }


    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if(!args.isBlank()) throw new WrongAmountOfArgsException();
        try {
            var response =(HeadResponse) client.sendAndReceiveCommand(new Request(CommandNames.HEAD, SessionHandler.getInstance().getCurrentUser()));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
            console.println(response.getHead().toString());
        } catch (ResponseErrorMessageException | ErrorResponseException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
