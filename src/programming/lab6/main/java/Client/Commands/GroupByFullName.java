package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.ResponseErrorMessageException;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.GroupCountByFullNameResponse;

import java.io.IOException;


public class GroupByFullName extends Command {
    private final Console console;
    private UDPClient client;


    public GroupByFullName(Console console,UDPClient client) {
        super(CommandNames.GROUP_COUNTING_BY_FULL_NAME);
        this.console = console;
        this.client = client;
    }

    @Override
    public void execute(String args) throws WrongAmountOfArgsException {
        if(!args.isBlank()) throw new WrongAmountOfArgsException();

        try {
            var response = (GroupCountByFullNameResponse) client.sendAndReceiveCommand(new Request(CommandNames.GROUP_COUNTING_BY_FULL_NAME));
            if (response.getStatus().equals(ResponseStatus.ERROR)){
                throw new ResponseErrorMessageException(response.getErrorMessage());
            }
            console.println(response.getResult().toString());
        }catch (ResponseErrorMessageException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
