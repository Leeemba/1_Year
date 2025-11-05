package Managers;

import Commands.Command;
import Commands.Executable;
import Exceptions.EmptyCollectionException;
import Exceptions.ExitPoint;
import Exceptions.WrongAmountOfArgsException;
import Exceptions.IncorrectCommandException;

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


    public Collection<Command> getCommands(){
        return commands.values();
    }
    public void addToHistory(String command){
        if(command.isBlank()) return;
        commandHistory.add(command);
    }
    public Queue<String> getCommandHistory(){
        return commandHistory;
    }


   public void doing(String name,String args) throws EmptyCollectionException, IncorrectCommandException, WrongAmountOfArgsException, ExitPoint {
        Executable command = commands.get(name);
        if (command == null ) throw new IncorrectCommandException();
        command.execute(args);
   }

}
