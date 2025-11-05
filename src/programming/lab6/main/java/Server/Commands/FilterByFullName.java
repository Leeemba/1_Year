package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.FilterByFullNameRequest;
import Common.Network.requests.Request;
import Common.Network.responses.FilterByFullNameResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;

import Server.Managers.CollectionManager;



public class FilterByFullName extends Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public FilterByFullName(Console console,CollectionManager collectionManager) {
        super(CommandNames.FILTER_BY_FULL_NAME,CommandNames.Descriptions.DESCR_FILTER_BY_FULL_NAME);
        this.console= console;
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
