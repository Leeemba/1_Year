package client.commands;

import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.ErrorResponseException;
import common.exceptions.WrongAmountOfArgsException;
import common.network.CommandNames;
import common.network.requests.FilterByFullNameRequest;
import common.network.responses.FilterByFullNameResponse;
import common.utility.ConsoleFormat;
import common.utility.Hint;

import java.io.IOException;

public class FilterByFullName extends  Command{
    private Console console;
    private UDPClient client;
    public FilterByFullName(Console console, UDPClient client){
        super(CommandNames.FILTER_BY_FULL_NAME);
        this.console = console;
        this.client = client;
    }

    @Override
    public void execute(String args) throws  WrongAmountOfArgsException{
        if (args.isBlank()) throw new WrongAmountOfArgsException();
        var fullName = args.trim();
        try {
            var response = (FilterByFullNameResponse) client.sendAndReceiveCommand(new FilterByFullNameRequest(fullName,SessionHandler.getInstance().getCurrentUser()));
            if (response.getResult().isEmpty()) console.printHint(Hint.FULLNAMEHINT.toString());
            response.getResult().forEach(org -> console.println(ConsoleFormat.coloring(org.toString(),ConsoleFormat.NONE,ConsoleFormat.BOLD)));
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }catch (ErrorResponseException e){
            console.printErr(e.getMessage());
        }
    }
}