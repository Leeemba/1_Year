package client.commands;
import client.network.UDPClient;
import client.utility.Console;
import client.utility.SessionHandler;
import common.exceptions.ErrorResponseException;
import common.exceptions.WrongAmountOfArgsException;
import common.network.CommandNames;
import common.network.requests.CountGreaterThanAnnualTurnoverRequest;
import common.network.responses.CountGreaterThanAnnualTurnoverResponse;
import common.utility.ConsoleFormat;

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
            var response = (CountGreaterThanAnnualTurnoverResponse) client.sendAndReceiveCommand(new CountGreaterThanAnnualTurnoverRequest(aT, SessionHandler.getInstance().getCurrentUser()));
            console.println(ConsoleFormat.coloring("Количество элементов коллекции, у которых годовой оборот больше заданного: " + response.getCount(), ConsoleFormat.GREEN, ConsoleFormat.BOLD));
        } catch (IOException e) {
            console.printErr("Ошибка во взаимодействии с сервером!Возможно сервер сейчас недоступен!");
        }catch (ErrorResponseException e){
            console.printErr(e.getMessage());
        }
    }
}
