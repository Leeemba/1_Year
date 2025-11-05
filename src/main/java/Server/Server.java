package Server;


import Common.Utility.ConsoleFormat;
import Common.Exceptions.ExitPoint;
import Server.Commands.*;
import Server.ConsoleOperations.Console;
import Server.Handler.CommandHandler;
import Server.Managers.CollectionManager;
import Server.Managers.CommandManager;
import Server.Managers.FileManager;
import Server.netWork.UDPDatagramServer;


import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.*;



public class Server
{
    public static final int PORT = 48230;
    public static void main( String[] args )
    {
        var console = new Console();

        if(args.length == 0) {
            console.printErr("Передайте что-нибудь в аргументах командной строки");
            System.exit(1);
            /*try {
                throw new ExitPoint();
            } catch (ExitPoint e) {
                console.print(ConsoleFormat.coloring("До встречи!", ConsoleFormat.GREEN, ConsoleFormat.ITALIC, ConsoleFormat.BOLD));
                return;
            }*/
        }
        FileManager fileManager = new FileManager(args[0],console);
        CollectionManager collectionManager = new CollectionManager(console,fileManager);
        //Runtime.getRuntime().addShutdownHook(new Thread(collectionManager::saveCollection));
        CommandManager commandManager = new CommandManager();


        try {
            //console.print(ConsoleFormat.coloring("Введите путь к файлу: ",ConsoleFormat.WHITE,ConsoleFormat.BOLD));
            fileManager.findFile();
            collectionManager.loadCollection();

        } catch (ExitPoint e) {
            //console.print(ConsoleFormat.coloring("До встречи!", ConsoleFormat.GREEN, ConsoleFormat.ITALIC, ConsoleFormat.BOLD));
            return;
        }



        commandManager.addCommands(List.of(new Head(console,collectionManager),
                new Clear(console,collectionManager),
                new Help(console,commandManager),
                new Add(collectionManager,console),
                new Show(collectionManager,console),
                new RemoveFirst(console,collectionManager),
                new RemoveById(console,collectionManager),
                new History(console,commandManager),
                new CountGreaterThanAnnualTurnover(console,collectionManager),
                new FilterByFullName(console,collectionManager),
                new Info(console,collectionManager),
                new GroupByFullName(console,collectionManager),
                new Update(console,collectionManager))
                /*new Save(console,collectionManager),
                new ExecuteScript(console,commandManager))*/
        );

        try {
            var server = new UDPDatagramServer(InetAddress.getLocalHost(), PORT, new CommandHandler(commandManager));
            server.setAfterHook(collectionManager::saveCollection);
            server.run();
        } catch (SocketException e) {
            console.printErr("случилась ошибка сокета");
        } catch (UnknownHostException e) {
            console.printErr("Неизвестный хост");
        }
    }
}



