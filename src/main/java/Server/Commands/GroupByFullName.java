package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.CountGreaterThanAnnualTurnoverResponse;
import Common.Network.responses.GroupCountByFullNameResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Common.Exceptions.EmptyCollectionException;
import Common.Exceptions.WrongAmountOfArgsException;
import Server.Managers.CollectionManager;

import static java.util.stream.Collectors.groupingBy;

public class GroupByFullName extends  Command{
    private final Console console;
    private final CollectionManager collectionManager;

    public GroupByFullName(Console console,CollectionManager collectionManager) {
        super(CommandNames.GROUP_COUNTING_BY_FULL_NAME,CommandNames.Descriptions.DESCR_GROUP_COUNTING_BY_FULL_NAME);
        this.console = console;
        this.collectionManager = collectionManager;
    }

    @Override
    public Response execute(Request request) {
    if (collectionManager.collectionSize() == 0) return new Response(CommandNames.GROUP_COUNTING_BY_FULL_NAME,"коллекция пуста,действие невозможно", ResponseStatus.ERROR);
    var res = collectionManager.groupByFullName();
    return new GroupCountByFullNameResponse(res,null,ResponseStatus.OK);
    }
}
