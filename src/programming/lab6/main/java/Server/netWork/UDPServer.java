package Server.netWork;

import Common.Network.ResponseStatus;
import Common.Network.requests.Request;
import Common.Network.responses.Response;
import Server.Handler.CommandHandler;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationException;
import org.apache.commons.lang3.SerializationUtils;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;

abstract class UDPServer {
    private final InetSocketAddress addr;
    private final CommandHandler commandHandler;
    private Runnable afterHook;

    private boolean running = true;


    public UDPServer(InetSocketAddress addr, CommandHandler commandHandler) {
        this.addr = addr;
        this.commandHandler = commandHandler;
    }

    public InetSocketAddress getAddr() {
        return addr;
    }

    /**
     * Получает данные с клиента.
     * Возвращает пару из данных и адреса клиента
     */
    public abstract Pair<Byte[], SocketAddress> receiveData() throws IOException;

    /**
     * Отправляет данные клиенту
     */
    public abstract void sendData(byte[] data, SocketAddress addr) throws IOException;

    public abstract void connectToClient(SocketAddress addr) throws SocketException;

    public abstract void disconnectFromClient();
    public abstract void close();

    public void run() {
        while (running) {
            Pair<Byte[], SocketAddress> dataPair;
            try {
                dataPair = receiveData();
            } catch (Exception e) {
                disconnectFromClient();
                continue;
            }

            var dataFromClient = dataPair.getKey();
            var clientAddr = dataPair.getValue();

            try {
                connectToClient(clientAddr);
            } catch (Exception e) {
                throw new RuntimeException("Ошибка соединения с клиентом : " + e.getMessage());
            }

            Request request;
            try {
                request = SerializationUtils.deserialize(ArrayUtils.toPrimitive(dataFromClient));
            } catch (SerializationException e) {
                disconnectFromClient();
                continue;
            }

            Response response ;
            try {
                response = commandHandler.handle(request);
                if (afterHook != null) afterHook.run();
            } catch (Exception e) {
                throw  new RuntimeException("Ошибка выполнения команды : " + e.getMessage());
            }
            if (response == null) response = new Response(null,"ошибка в запросе: "+request.getName(), ResponseStatus.ERROR);
            var data = SerializationUtils.serialize(response);
            try {
                sendData(data, clientAddr);

            } catch (Exception e) {
                throw  new RuntimeException("Ошибка ввода-вывода : " + e.getMessage());
            }

            disconnectFromClient();

        }

        close();
    }

    /**
     * Вызывает хук после каждого запроса.
     * @param afterHook хук, вызываемый после каждого запроса
     */
    public void setAfterHook(Runnable afterHook) {
        this.afterHook = afterHook;
    }

    public void stop() {
        running = false;
    }
}