package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.ResponseErrorMessageException;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.RemoveByIdRequest;
import Common.Network.responses.RemoveByIdResponse;
import Common.Utility.ConsoleFormat;

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
            var response = (RemoveByIdResponse) client.sendAndReceiveCommand(new RemoveByIdRequest(id));
            if (response.getStatus().equals(ResponseStatus.ERROR)) throw  new ResponseErrorMessageException(response.getErrorMessage());
            console.println(ConsoleFormat.coloring(response.getMessage(),ConsoleFormat.GREEN,ConsoleFormat.ITALIC));

        }catch (NumberFormatException e){
            console.printErr("Id должно быть числом типа int!");
        }catch (ResponseErrorMessageException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
