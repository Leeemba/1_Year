package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.RemoveFirstResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Server.Managers.CollectionManager;

public class RemoveFirst extends Command{
    private final CollectionManager collection;
    private final Console console;
    private final static String NAME = "remove_first";
    private final static String DESCRIPTION = "удалить первый элемент из коллекции";
    public RemoveFirst(Console console,CollectionManager collection) {
        super(CommandNames.REMOVE_FIRST, CommandNames.Descriptions.DESCR_REMOVE_FIRST);
        this.collection =collection;
        this.console=console;
    }

    @Override
    public Response execute(Request request) {
        if (collection.collectionSize() == 0) {
            return new Response(CommandNames.REMOVE_FIRST,"Коллекция капуста", ResponseStatus.ERROR);
        }else{
            collection.removeFirst();
            return new RemoveFirstResponse(null,ResponseStatus.OK,"Успешно удален первый элемент коллекции");
        }
    }
}
