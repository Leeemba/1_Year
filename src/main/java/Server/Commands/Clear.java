package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.ClearResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Common.Utility.ConsoleFormat;
import Common.Exceptions.EmptyCollectionException;
import Common.Exceptions.WrongAmountOfArgsException;
import Server.Managers.CollectionManager;

public class Clear extends Command implements Executable{
    private final Console console;
    private final CollectionManager collectionManager;

    public Clear(Console console,CollectionManager collectionManager) {
        super(CommandNames.CLEAR,CommandNames.Descriptions.DESCR_CLEAR);
        this.console=console;
        this.collectionManager = collectionManager;
    }



    @Override
    public Response execute(Request request)  {
        if (collectionManager.collectionSize() == 0) {
            return new ClearResponse("Невозможно выполнить действие.Коллекция пуста!", ResponseStatus.ERROR,null);
        }
        collectionManager.removeAll();
        return new ClearResponse(null,ResponseStatus.OK,"Коллекция очищена успешно!");
    }

}
