package server.commands;

import common.exceptions.IncorrectOwnerException;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.RemoveFirstResponse;
import common.network.responses.Response;
import server.managers.CollectionManager;

public class RemoveFirst extends Command{
    private final CollectionManager collection;

    public RemoveFirst(CollectionManager collection) {
        super(CommandNames.REMOVE_FIRST, CommandNames.Descriptions.DESCR_REMOVE_FIRST);
        this.collection =collection;
    }

    @Override
    public Response execute(Request request) {
        if (collection.collectionSize() == 0) {
            return new Response(CommandNames.REMOVE_FIRST,"Коллекция капуста", ResponseStatus.ERROR);
        }else{
            try {
                var isRem =collection.removeFirst(request.getUser().getLogin());
                if (!isRem) return new RemoveFirstResponse(null,ResponseStatus.OK,"Ничего не было удалено.Возможно указанные id и логин неверны");
                return new RemoveFirstResponse(null,ResponseStatus.OK,"Успешно удален первый элемент коллекции");
            } catch (IncorrectOwnerException e) {
                return new RemoveFirstResponse("Вы пытаетесь изменить чужое", ResponseStatus.ERROR, null);
            }
        }
    }
}
