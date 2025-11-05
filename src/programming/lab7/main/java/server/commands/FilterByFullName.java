package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.FilterByFullNameRequest;
import common.network.requests.Request;
import common.network.responses.FilterByFullNameResponse;
import common.network.responses.Response;

import server.managers.CollectionManager;



public class FilterByFullName extends Command{

    private final CollectionManager collectionManager;

    public FilterByFullName(CollectionManager collectionManager) {
        super(CommandNames.FILTER_BY_FULL_NAME,CommandNames.Descriptions.DESCR_FILTER_BY_FULL_NAME);

        this.collectionManager =collectionManager;
    }

    @Override
    public Response execute(Request request) {

        var reqData = (FilterByFullNameRequest) request;
        var fullName = reqData.getFullName();
        var temp = collectionManager.filterByFullName(fullName);
        return new FilterByFullNameResponse(temp,null, ResponseStatus.OK);

    }
}

