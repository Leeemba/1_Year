package Client.Commands;

import Client.network.UDPClient;
import Client.utility.Console;
import Common.Exceptions.WrongAmountOfArgsException;
import Common.Network.CommandNames;
import Common.Network.requests.CountGreaterThanAnnualTurnoverRequest;
import Common.Network.responses.CountGreaterThanAnnualTurnoverResponse;
import Common.Utility.ConsoleFormat;

import java.io.IOException;

public class CountGreaterThanAnnualTurnover extends Command{
    private UDPClient client;
    private Console console;
    public CountGreaterThanAnnualTurnover(Console console, UDPClient client){
        super(CommandNames.COUNT_GREATER_THAN_ANNUAL_TURNOVER);
        this.console = console;
        this.client = client;
    }

    @Override
    public void execute(String args) throws  WrongAmountOfArgsException {
        if (args.isBlank()) throw new WrongAmountOfArgsException();
        try {
            var aT = Integer.parseInt(args.trim());
            var response = (CountGreaterThanAnnualTurnoverResponse) client.sendAndReceiveCommand(new CountGreaterThanAnnualTurnoverRequest(aT));
            console.println(ConsoleFormat.coloring("Количество элементов коллекции, у которых годовой оборот больше заданного: " + response.getCount(), ConsoleFormat.GREEN, ConsoleFormat.BOLD));
        } catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }
    }
}
