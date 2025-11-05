package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.ClearResponse;
import common.network.responses.Response;
import server.db.OrganizationRepo;
import server.managers.CollectionManager;

public class Clear extends Command implements Executable{

    private final CollectionManager collectionManager;

    public Clear(CollectionManager collectionManager) {
        super(CommandNames.CLEAR,CommandNames.Descriptions.DESCR_CLEAR);
        this.collectionManager = collectionManager;
    }



    @Override
    public Response execute(Request request)  {
        if (collectionManager.collectionSize() == 0) {
            return new ClearResponse("Невозможно выполнить действие.Коллекция пуста!", ResponseStatus.ERROR,null);
        }
        int linesRemoved = OrganizationRepo.deleteAllByUser(request.getUser().getLogin());
        collectionManager.removeAll(request.getUser().getLogin());
        if (linesRemoved == 0) return new ClearResponse("В базе данных нет объектов,принадлежащих Вам. Ничего не было удалено!",ResponseStatus.ERROR,null);

        return new ClearResponse(null,ResponseStatus.OK,"Коллекция очищена успешно!");
    }

}
