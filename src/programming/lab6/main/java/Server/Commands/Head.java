package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.HeadResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Server.Managers.CollectionManager;

public class Head extends Command{
    private final CollectionManager collection;
    private final Console console;

    public Head(Console console,CollectionManager collection) {
        super(CommandNames.HEAD,CommandNames.Descriptions.DESCR_HEAD);
        this.collection =collection;
        this.console = console;
    }


    @Override
    public Response execute(Request request) {
        if(collection.collectionSize() == 0) return new Response(CommandNames.HEAD,"Коллекция пуста!", ResponseStatus.ERROR);
        var head =collection.getFirst();
        return new HeadResponse(null,ResponseStatus.OK,head);
    }
}
