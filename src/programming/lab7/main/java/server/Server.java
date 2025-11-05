package server;


import server.commands.*;
import server.consoleOperations.Console;
import server.db.DatabaseInitializer;
import server.db.OrganizationRepo;
import server.handler.CommandHandler;
import server.managers.AuthManager;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.netWork.UDPDatagramServer;


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
        AuthManager authManager = new AuthManager("pepper");
        DatabaseInitializer.init();
        OrganizationRepo repo = new OrganizationRepo();
        CollectionManager collectionManager = new CollectionManager(console,repo);
        CommandManager commandManager = new CommandManager();



        commandManager.addCommands(List.of(new Head(collectionManager),
                new Authenticate(authManager),
                new Register(authManager),
                new Clear(collectionManager),
                new Help(commandManager),
                new Add(collectionManager),
                new Show(collectionManager),
                new RemoveFirst(collectionManager),
                new RemoveById(collectionManager),
                new History(commandManager),
                new CountGreaterThanAnnualTurnover(collectionManager),
                new FilterByFullName(collectionManager),
                new Info(collectionManager),
                new GroupByFullName(collectionManager),
                new Update(collectionManager))
        );

        try {
            var server = new UDPDatagramServer(InetAddress.getLocalHost(), PORT, new CommandHandler(commandManager,authManager));
            server.run();
        } catch (SocketException e) {
            console.printErr("случилась ошибка сокета");
        } catch (UnknownHostException e) {
            console.printErr("Неизвестный хост");
        }
    }





}



