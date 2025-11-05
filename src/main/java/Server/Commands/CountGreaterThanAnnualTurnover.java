package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.CountGreaterThanAnnualTurnoverRequest;
import Common.Network.requests.Request;
import Common.Network.responses.CountGreaterThanAnnualTurnoverResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Common.Utility.ConsoleFormat;
import Common.Exceptions.WrongAmountOfArgsException;
import Server.Managers.CollectionManager;

public class CountGreaterThanAnnualTurnover extends  Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public CountGreaterThanAnnualTurnover(Console console,CollectionManager collectionManager) {
        super(CommandNames.COUNT_GREATER_THAN_ANNUAL_TURNOVER, CommandNames.Descriptions.DESCR_COUNT_GREATER_THAN_ANNUAL_TURNOVER);
        this.console=console;
        this.collectionManager=collectionManager;
    }

    @Override
    public Response execute(Request request) {
        var reqData = (CountGreaterThanAnnualTurnoverRequest) request;
        long counted = collectionManager.countGreatByAnnTur(reqData.getaT());
        return new CountGreaterThanAnnualTurnoverResponse(null, ResponseStatus.OK,counted);

    }
}
