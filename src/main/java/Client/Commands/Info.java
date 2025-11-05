package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.requests.Request;
import Common.Network.responses.InfoResponse;

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
            var response = (InfoResponse) client.sendAndReceiveCommand(new Request(CommandNames.INFO));

        console.print(response.getInfo().toString());
    }catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }

}
