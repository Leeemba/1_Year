package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.HistoryResponse;
import common.network.responses.Response;
import common.utility.ConsoleFormat;
import server.managers.CommandManager;

public class History extends Command{

    private final CommandManager commandManager;

    public History(CommandManager commandManager) {
        super(CommandNames.HISTORY,CommandNames.Descriptions.DESCR_HISTORY);
        this.commandManager =commandManager;
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
