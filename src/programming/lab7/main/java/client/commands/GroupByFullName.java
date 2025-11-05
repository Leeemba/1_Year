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
import common.network.responses.GroupCountByFullNameResponse;

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
            var response = (GroupCountByFullNameResponse) client.sendAndReceiveCommand(new Request(CommandNames.GROUP_COUNTING_BY_FULL_NAME, SessionHandler.getInstance().getCurrentUser()));
            if (response.getStatus().equals(ResponseStatus.ERROR)){
                throw new ResponseErrorMessageException(response.getErrorMessage());
            }
            console.println(response.getResult().toString());
        }catch (ResponseErrorMessageException | ErrorResponseException e){
            console.printErr(e.getMessage());
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
