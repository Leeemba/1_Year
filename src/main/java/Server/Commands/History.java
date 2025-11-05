package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.HistoryResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Common.Utility.ConsoleFormat;
import Common.Utility.Hint;
import Common.Exceptions.WrongAmountOfArgsException;
import Server.Managers.CommandManager;

public class History extends Command{
    private final Console console;
    private final CommandManager commandManager;

    public History(Console console,CommandManager commandManager) {
        super(CommandNames.HISTORY,CommandNames.Descriptions.DESCR_HISTORY);
        this.commandManager =commandManager;
        this.console =console;
    }

    @Override
    public Response execute(Request request) {
    var history = commandManager.getCommandHistory();
    StringBuilder sb=new StringBuilder(" ");
    if( history.isEmpty()) {
        return new Response(CommandNames.HISTORY,"Коллекция капуста!Невозможно выполнить действие", ResponseStatus.ERROR);
    }else{
        history.stream()
                .skip(Math.max(history.size() - 9, 0))
                .forEach(s -> sb.append(ConsoleFormat.coloring(s + "||",ConsoleFormat.GREEN,ConsoleFormat.BOLD)));
    }
    return new HistoryResponse(null,ResponseStatus.OK,sb);
    }
}
