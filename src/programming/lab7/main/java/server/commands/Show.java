package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.Response;
import common.network.responses.ShowResponse;
import server.managers.CollectionManager;

public class Show extends Command {
    private final CollectionManager collectionManager ;


    public Show(CollectionManager collectionManager) {
        super(CommandNames.SHOW, CommandNames.Descriptions.DESCR_SHOW);
        this.collectionManager = collectionManager;

    }


    @Override
    public Response execute(Request request)  {
        if (collectionManager.collectionSize() == 0){
            return new ShowResponse("Коллекция капуста!Действие невозможно", ResponseStatus.ERROR,null);}
        else {
            var collection = collectionManager.exportCollection();
            return new ShowResponse(null,ResponseStatus.OK,collection);
        }

    }
}
