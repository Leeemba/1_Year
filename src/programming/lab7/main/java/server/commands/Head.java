package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.HeadResponse;
import common.network.responses.Response;
import server.managers.CollectionManager;

public class Head extends Command{
    private final CollectionManager collection;


    public Head(CollectionManager collection) {
        super(CommandNames.HEAD,CommandNames.Descriptions.DESCR_HEAD);
        this.collection =collection;

    }


    @Override
    public Response execute(Request request) {
        if(collection.collectionSize() == 0) return new Response(CommandNames.HEAD,"Коллекция пуста!", ResponseStatus.ERROR);
        var head =collection.getFirst();
        return new HeadResponse(null,ResponseStatus.OK,head);
    }
}

