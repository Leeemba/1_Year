package Server.Managers;

import Server.Commands.Command;

import java.util.*;
import java.util.stream.Collectors;

public class CommandManager  {

    private final Map<String,Command> commands = new TreeMap<>();

    private final Queue<String> commandHistory = new LinkedList<>();


    public void addCommands(List<Command> commands){
        this.commands.putAll(commands
                .stream()
                .collect(Collectors.toMap(Command::getName, s -> s)));


        /* for (Command command:commands) {
            this.commands.put(command.getName(),command);
        }*/
    }


    public Map<String,Command> getCommands(){
        return commands;
    }
    public void addToHistory(String command){
        if(command.isBlank()) return;
        commandHistory.add(command);
    }
    public Queue<String> getCommandHistory(){
        return commandHistory;
    }


   /*public void doing(String name,String args) throws EmptyCollectionException, IncorrectCommandException, WrongAmountOfArgsException, ExitPoint {
        Executable command = commands.get(name);
        if (command == null ) throw new IncorrectCommandException();
        command.execute(args);
   }*/

}
