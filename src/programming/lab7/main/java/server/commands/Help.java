package server.commands;

import common.network.CommandNames;
import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.HelpResponse;
import common.network.responses.Response;

import server.managers.CommandManager;

import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Help extends Command{

    private final CommandManager commandManager;


    public Help(CommandManager commandManager ){
        super(CommandNames.HELP,CommandNames.Descriptions.DESCR_HELP);
        this.commandManager = commandManager;
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

