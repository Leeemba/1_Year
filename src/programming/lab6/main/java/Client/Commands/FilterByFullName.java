package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.requests.FilterByFullNameRequest;
import Common.Network.responses.FilterByFullNameResponse;
import Common.Utility.ConsoleFormat;
import Common.Utility.Hint;

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
            var response = (FilterByFullNameResponse) client.sendAndReceiveCommand(new FilterByFullNameRequest(fullName));
            if (response.getResult().isEmpty()) console.printHint(Hint.FULLNAMEHINT.toString());
            response.getResult().forEach(org -> console.println(ConsoleFormat.coloring(org.toString(),ConsoleFormat.NONE,ConsoleFormat.BOLD)));
        }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
