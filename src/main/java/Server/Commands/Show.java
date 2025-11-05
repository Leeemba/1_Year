package Server.Commands;

import Common.Models.Organization;
import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.Response;
import Common.Network.responses.ShowResponse;
import Server.ConsoleOperations.Console;
import Common.Utility.ConsoleFormat;
import Common.Exceptions.EmptyCollectionException;
import Common.Exceptions.WrongAmountOfArgsException;
import Server.Managers.CollectionManager;

import java.util.ArrayDeque;
import java.util.stream.Collectors;

public class Show extends Command {
    private final CollectionManager collectionManager ;
    private final Console console;

    public Show(CollectionManager collectionManager,Console console) {
        super(CommandNames.SHOW, CommandNames.Descriptions.DESCR_SHOW);
        this.collectionManager = collectionManager;
        this.console =console;
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
