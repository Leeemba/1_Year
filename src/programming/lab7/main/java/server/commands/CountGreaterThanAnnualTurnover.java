package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.CountGreaterThanAnnualTurnoverRequest;
import common.network.requests.Request;
import common.network.responses.CountGreaterThanAnnualTurnoverResponse;
import common.network.responses.Response;
import server.managers.CollectionManager;

public class CountGreaterThanAnnualTurnover extends  Command{

    private final CollectionManager collectionManager;

    public CountGreaterThanAnnualTurnover(CollectionManager collectionManager) {
        super(CommandNames.COUNT_GREATER_THAN_ANNUAL_TURNOVER, CommandNames.Descriptions.DESCR_COUNT_GREATER_THAN_ANNUAL_TURNOVER);
        this.collectionManager=collectionManager;
    }

    @Override
    public Response execute(Request request) {
        var reqData = (CountGreaterThanAnnualTurnoverRequest) request;
        long counted = collectionManager.countGreatByAnnTur(reqData.getaT());
        return new CountGreaterThanAnnualTurnoverResponse(null, ResponseStatus.OK,counted);

    }
}
