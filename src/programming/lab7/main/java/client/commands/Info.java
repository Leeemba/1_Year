package client.commands;

import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.ErrorResponseException;
import common.exceptions.WrongAmountOfArgsException;
import common.network.CommandNames;
import common.network.requests.Request;
import common.network.responses.InfoResponse;

import java.io.IOException;

public class Info extends  Command{
    private UDPClient client;
    private Console console;
    public Info(Console console,UDPClient client){
        super(CommandNames.INFO);
        this.console =console;
        this.client = client;
    }
    @Override
    public void execute(String args) throws  WrongAmountOfArgsException{
        if (!args.isBlank()) throw  new WrongAmountOfArgsException();
        try {
            var response = (InfoResponse) client.sendAndReceiveCommand(new Request(CommandNames.INFO, SessionHandler.getInstance().getCurrentUser()));

            console.print(response.getInfo().toString());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }catch (ErrorResponseException e){
            console.printErr(e.getMessage());
        }
    }

}
