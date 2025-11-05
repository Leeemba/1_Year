package server.commands;
import common.exceptions.IncorrectOwnerException;
import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.requests.UpdateRequest;
import common.network.responses.Response;
import common.network.responses.UpdateResponse;
import server.managers.CollectionManager;

public class Update extends Command {
    private final CollectionManager collectionManager;



    public Update(CollectionManager collectionManager){
        super(CommandNames.UPDATE,CommandNames.Descriptions.DESCR_UPDATE);
        this.collectionManager =collectionManager;

    }

    @Override
    public Response execute(Request request) {
        var reqData = (UpdateRequest) request;
        try {
            if(reqData.getUpdOrg() == null){
                if (!collectionManager.checkExist(reqData.getId())) return new UpdateResponse("Объекта с таким id нет в списке!", ResponseStatus.ERROR, null);
                if (!collectionManager.checkCreator(reqData.getId(), reqData.getUser().getLogin())) throw new IncorrectOwnerException();
            }
            //if (!(reqData.getUpdOrg().validate())) return new UpdateResponse("Поля объекта невалидны,объект не создан!", ResponseStatus.ERROR, null);
            if (collectionManager.collectionSize() == 0) return new UpdateResponse("Коллекция капуста!Действие невозможно", ResponseStatus.ERROR, null);
            if (!collectionManager.checkExist(reqData.getId())) return new UpdateResponse("Объекта с таким id нет в списке!", ResponseStatus.ERROR, null);
            var updated=collectionManager.editById(reqData.getId(), reqData.getUpdOrg(), reqData.getUser().getLogin());
            if (!updated) return new UpdateResponse(null,ResponseStatus.OK,"Ничего не было обновлено");
            return new UpdateResponse(null, ResponseStatus.OK, "Объект с присланным id успешно обновлен!");
        } catch (IncorrectOwnerException e) {
            return  new UpdateResponse("Вы пытаетесь изменить чужое",ResponseStatus.ERROR,null);
        }catch (Exception e){
            return new UpdateResponse(e.toString(),ResponseStatus.ERROR,null);
        }

    }
}
