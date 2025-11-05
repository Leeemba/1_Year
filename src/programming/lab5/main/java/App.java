import Commands.*;
import ConsoleOperations.*;
import Exceptions.ExitPoint;
import Managers.*;


import java.util.*;



public class App 
{
    public static void main( String[] args )
    {
        var console = new Console();

        if(args.length == 0) {
            console.printErr("Передайте что-нибудь в аргументах командной строки");
            try {
                throw new ExitPoint();
            } catch (ExitPoint e) {
                console.print(ConsoleFormat.coloring("До встречи!", ConsoleFormat.GREEN, ConsoleFormat.ITALIC, ConsoleFormat.BOLD));
                return;
            }
        }

        ScannerManager.getScannerManager().setUserScanner(new Scanner(System.in));
        CommandManager commandManager = new CommandManager();
        FileManager fileManager = new FileManager(args[0],console);
        CollectionManager collectionManager = new CollectionManager(console,fileManager);
        try {
            console.print(ConsoleFormat.coloring("Введите путь к файлу: ",ConsoleFormat.WHITE,ConsoleFormat.BOLD));
            fileManager.findFile();
            collectionManager.loadCollection();

        } catch (ExitPoint e) {
            console.print(ConsoleFormat.coloring("До встречи!", ConsoleFormat.GREEN, ConsoleFormat.ITALIC, ConsoleFormat.BOLD));
            return;
        }


        RuntimeManager runtimeManager = new RuntimeManager(console,commandManager);

        commandManager.addCommands(List.of(new Head(console,collectionManager),
                new Clear(console,collectionManager),
                new Help(console,commandManager),
                new Add(collectionManager,console),
                new Show(collectionManager,console),
                new RemoveFirst(console,collectionManager),
                new RemoveById(console,collectionManager),
                new Exit(),
                new History(console,commandManager),
                new CountGreaterThanAnnualTurnover(console,collectionManager),
                new FilterByFullName(console,collectionManager),
                new Info(console,collectionManager),
                new GroupByFullName(console,collectionManager),
                new Update(console,collectionManager),
                new Save(console,collectionManager),
                new ExecuteScript(console,commandManager,runtimeManager))
        );


       runtimeManager.interactiveMode();



    }
}



