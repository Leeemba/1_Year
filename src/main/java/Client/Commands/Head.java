package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.EmptyCollectionException;
import Common.Exceptions.ResponseErrorMessageException;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.HeadResponse;

import java.io.IOException;


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
            var response =(HeadResponse) client.sendAndReceiveCommand(new Request(CommandNames.HEAD));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw new ResponseErrorMessageException(response.getErrorMessage());
            console.println(response.getHead().toString());
        } catch (ResponseErrorMessageException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
