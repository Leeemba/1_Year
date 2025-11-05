package Server.Commands;

import Common.Network.CommandNames;
import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.HelpResponse;
import Common.Network.responses.Response;
import Server.ConsoleOperations.Console;
import Common.Utility.ConsoleFormat;

import Server.Managers.CommandManager;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Help extends Command{

    private final CommandManager commandManager;
    private final Console console;

    public Help(Console console,CommandManager commandManager ){
        super(CommandNames.HELP,CommandNames.Descriptions.DESCR_HELP);
        this.commandManager = commandManager;
        this.console =console;
    }



    @Override
    public Response execute(Request request){
        var mapOfCommands = this.commandManager.getCommands();
        TreeMap<String,String> commands=mapOfCommands.entrySet().
                stream().
                collect(Collectors.toMap(Map.Entry::getKey,s->s.getValue().getDescription(),(e1,e2)->e1,TreeMap::new));
        return new HelpResponse(null, ResponseStatus.OK,commands);


    }



}
