package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.GroupCountByFullNameResponse;
import common.network.responses.Response;
import server.managers.CollectionManager;

import static java.util.stream.Collectors.groupingBy;

public class GroupByFullName extends  Command{

    private final CollectionManager collectionManager;

    public GroupByFullName(CollectionManager collectionManager) {
        super(CommandNames.GROUP_COUNTING_BY_FULL_NAME,CommandNames.Descriptions.DESCR_GROUP_COUNTING_BY_FULL_NAME);
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
        if (collectionManager.collectionSize() == 0) return new Response(CommandNames.GROUP_COUNTING_BY_FULL_NAME,"коллекция пуста,действие невозможно", ResponseStatus.ERROR);
        var res = collectionManager.groupByFullName();
        return new GroupCountByFullNameResponse(res,null,ResponseStatus.OK);
    }
}
