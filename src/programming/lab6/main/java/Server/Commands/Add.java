package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.AddRequest;
import Common.Network.requests.Request;
import Common.Network.responses.AddResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Server.Managers.CollectionManager;


public class Add extends Command implements  Executable{
    private final CollectionManager collectionManager;
    private final Console console;

    public Add(CollectionManager collectionManager,Console console) {
        super(CommandNames.ADD,CommandNames.Descriptions.DESCR_ADD);
        this.collectionManager = collectionManager;
        this.console = console;
    }


    @Override
    public Response execute(Request request)  {
        var requestData = (AddRequest) request;
        if(!(requestData.getOrganization().validate())){
            return new AddResponse(-1,"Поля объекта невалидны,объект не создан!", ResponseStatus.ERROR);
        }
        var orgId = collectionManager.addElement(requestData.getOrganization());
        return new AddResponse(orgId,null,ResponseStatus.OK);

    }

}
