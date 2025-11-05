package Client;

import Client.network.UDPClient;
import Client.utility.Console;
import Client.utility.RuntimeManager;

import java.io.IOException;
import java.net.InetAddress;

public class Client {
    private static final int PORT = 48230;


    public static void main(String[] args) {
        var console = new Console();
        try {
        var client = new UDPClient(InetAddress.getLocalHost(),PORT);
        var runtimeManager = new RuntimeManager(console,client);

        runtimeManager.interactiveMode();
    }catch (IOException e){
            console.printErr("Произошла ошибка при попытке соединения с сервером!Возможно сервер сейчас недоступен!");
        }

    }
}
