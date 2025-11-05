package server.netWork;

import common.network.ResponseStatus;
import common.network.requests.Request;
import common.network.responses.ErrorResponse;
import common.network.responses.Response;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.SerializationException;


import org.apache.commons.lang3.tuple.Pair;
import org.apache.commons.lang3.SerializationUtils;

import server.handler.CommandHandler;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * UDP обработчик запросов

 */
abstract class UDPServer {
    private static final int READ_POOL_SIZE = 4;

    private final InetSocketAddress addr;
    private final CommandHandler commandHandler;
    private final ExecutorService service;



    private boolean running = true;

    public UDPServer(InetSocketAddress addr, CommandHandler commandHandler) {
        this.addr = addr;
        this.commandHandler = commandHandler;
        this.service = Executors.newFixedThreadPool(READ_POOL_SIZE);
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


        service.submit(() -> {
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
                    throw new RuntimeException("Ошибка соединения с клиентом : " + e.toString(), e);
                }

                Request request;
                try {
                    request = SerializationUtils.deserialize(ArrayUtils.toPrimitive(dataFromClient));

                } catch (SerializationException e) {
                    disconnectFromClient();
                    continue;
                }

                new Thread(() -> {
                    Response response = null;
                    try {
                        response = commandHandler.handle(request);
                    } catch (Exception e) {
                      throw new RuntimeException("Ошибка выполнения команды : " + e.toString(), e);
                    }
                    if (response == null) response = new ErrorResponse(null,request.getName(), ResponseStatus.ERROR);

                    var data = SerializationUtils.serialize(response);


                    new Thread(() -> {
                        try {
                            sendData(data, clientAddr);

                        } catch (Exception e) {
                            throw new RuntimeException("Ошибка ввода-вывода : " + e.toString(), e);
                        }
                    }).start();
                }).start();

                disconnectFromClient();
            }
            close();
        });
    }

    public void stop() {
        running = false;
    }
}