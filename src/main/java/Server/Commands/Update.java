package Server.Commands;

import Common.Exceptions.*;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.requests.UpdateRequest;
import Common.Network.responses.AddResponse;
import Common.Network.responses.Response;
import Common.Network.responses.UpdateResponse;
import Server.ConsoleOperations.Console;
import Common.Utility.ConsoleFormat;
import Server.Managers.CollectionManager;
import Client.Forms.OrganizationForm;

public class Update extends Command {
    private final CollectionManager collectionManager;
    private final Console console;

    public Update(Console console,CollectionManager collectionManager){
        super(CommandNames.UPDATE,CommandNames.Descriptions.DESCR_UPDATE);
        this.collectionManager =collectionManager;
        this.console =console;
    }

    @Override
    public Response execute(Request request) {
        var reqData = (UpdateRequest) request;
        if(!(reqData.getUpdOrg().validate()))return new UpdateResponse("Поля объекта невалидны,объект не создан!", ResponseStatus.ERROR,-1);
        if (collectionManager.collectionSize() == 0) return new UpdateResponse("Коллекция капуста!Действие невозможно", ResponseStatus.ERROR,-1);;
        if(!collectionManager.checkExist(reqData.getId())) return new UpdateResponse("Объекта с таким id нет в списке!",ResponseStatus.ERROR,-1);
        collectionManager.editById(reqData.getId(),reqData.getUpdOrg());
        return new UpdateResponse(null,ResponseStatus.OK,reqData.getId());
    }
}
