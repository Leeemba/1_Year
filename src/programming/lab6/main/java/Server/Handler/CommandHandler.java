package Server.Handler;

import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.Response;
import Server.Managers.CommandManager;

public class CommandHandler {
    private final CommandManager commandManager;
    public CommandHandler(CommandManager commandManager){
        this.commandManager = commandManager;
    }

    public Response handle(Request request){
        var command = commandManager.getCommands().get(request.getName());
        if (command == null) return new Response(ResponseStatus.ERROR);
        commandManager.addToHistory(command.getName());
        return command.execute(request);
    }

}
