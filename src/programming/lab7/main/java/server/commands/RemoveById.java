package server.commands;
import common.exceptions.IncorrectOwnerException;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.RemoveByIdRequest;
import common.network.requests.Request;
import common.network.responses.RemoveByIdResponse;
import common.network.responses.Response;
import server.managers.CollectionManager;

public class RemoveById  extends  Command{
    private final CollectionManager collectionManager;


    public RemoveById(CollectionManager collectionManager) {
        super(CommandNames.REMOVE_BY_ID, CommandNames.Descriptions.DESCR_REMOVE_BY_ID);
        this.collectionManager =collectionManager;

    }

    @Override
    public Response execute(Request request){
        var reqData = (RemoveByIdRequest) request;
        if (collectionManager.collectionSize() == 0) return new RemoveByIdResponse("Коллекция капуста!Действие невозможно", ResponseStatus.ERROR,null);
        if (!collectionManager.checkExist(reqData.getId())) return new RemoveByIdResponse("Объекта с таким id нет в списке!",ResponseStatus.ERROR,null);
        try {
            var isRemoved = collectionManager.removeElement(reqData.getId(),reqData.getUser().getLogin());
            if (!isRemoved) return new RemoveByIdResponse(null,ResponseStatus.OK,"Ничего не было удалено.Возможно указанные id и логин неверны");
            return new RemoveByIdResponse(null,ResponseStatus.OK,"Объект с id: "+reqData.getId()+" успешно удален.");
        } catch (IncorrectOwnerException e) {
            return  new RemoveByIdResponse("Вы пытаетесь изменить чужое",ResponseStatus.ERROR,null);
        }catch (Exception e){
            return  new RemoveByIdResponse(e.toString(),ResponseStatus.ERROR,null);
        }


    }
}
