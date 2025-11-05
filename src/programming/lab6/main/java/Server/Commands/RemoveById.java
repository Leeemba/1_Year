package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.RemoveByIdRequest;
import Common.Network.requests.Request;
import Common.Network.responses.RemoveByIdResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Server.Managers.CollectionManager;

public class RemoveById  extends  Command{
    private final CollectionManager collectionManager;
    private final Console console;

    public RemoveById(Console console,CollectionManager collectionManager) {
        super(CommandNames.REMOVE_BY_ID, CommandNames.Descriptions.DESCR_REMOVE_BY_ID);
        this.collectionManager =collectionManager;
        this.console =console;
    }

    @Override
    public Response execute(Request request){
        var reqData = (RemoveByIdRequest) request;
        if (collectionManager.collectionSize() == 0) return new RemoveByIdResponse("Коллекция капуста!Действие невозможно", ResponseStatus.ERROR,null);
        if (!collectionManager.checkExist(reqData.getId())) return new RemoveByIdResponse("Объекта с таким id нет в списке!",ResponseStatus.ERROR,null);
        collectionManager.removeElement(collectionManager.getById(reqData.getId()));
        return new RemoveByIdResponse(null,ResponseStatus.OK,"Объект с id: "+reqData.getId()+" успешно удален");

    }
}
