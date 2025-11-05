package server.netWork;

import server.handler.CommandHandler;
import com.google.common.primitives.Bytes;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Arrays;

public class UDPDatagramServer extends UDPServer {
    private final int PACKET_SIZE = 1024;
    private final int DATA_SIZE = PACKET_SIZE - 1;

    private final DatagramSocket datagramSocket;

    public UDPDatagramServer(InetAddress address, int port, CommandHandler commandHandler) throws SocketException {
        super(new InetSocketAddress(address, port), commandHandler);
        this.datagramSocket = new DatagramSocket(getAddr());
        this.datagramSocket.setReuseAddress(true);
    }

    @Override
    public Pair<Byte[], SocketAddress> receiveData() throws IOException {
        var received = false;
        var result = new byte[0];
        SocketAddress addr = null;

        while(!received) {
            var buf = new byte[PACKET_SIZE];

            var dp = new DatagramPacket(buf, PACKET_SIZE);
            datagramSocket.receive(dp);

            addr = dp.getSocketAddress();

            if (buf[buf.length - 1] == 1) {
                received = true;
            }
            result = Bytes.concat(result, Arrays.copyOf(buf, buf.length - 1));
        }
        return new ImmutablePair<>(ArrayUtils.toObject(result), addr);
    }

    @Override
    public void sendData(byte[] buf, SocketAddress addr) throws IOException {
        byte[][] ret = new byte[(int)Math.ceil(buf.length / (double)DATA_SIZE)][DATA_SIZE];

        int start = 0;
        for(int i = 0; i < ret.length; i++) {
            ret[i] = Arrays.copyOfRange(buf, start, start + DATA_SIZE);
            start += DATA_SIZE;
        }


        for(int i = 0; i < ret.length; i++) {
            var chunk = ret[i];
            if (i == ret.length - 1) {
                var lastChunk = Bytes.concat(chunk, new byte[]{1});
                var dp = new DatagramPacket(lastChunk, PACKET_SIZE, addr);
                datagramSocket.send(dp);
            } else {
                var dp = new DatagramPacket(ByteBuffer.allocate(PACKET_SIZE).put(chunk).array(), PACKET_SIZE, addr);
                datagramSocket.send(dp);
            }
        }
    }

    @Override
    public void connectToClient(SocketAddress addr) throws SocketException {
        datagramSocket.connect(addr);
    }

    @Override
    public void disconnectFromClient() {
        datagramSocket.disconnect();
    }

    @Override
    public void close() {
        datagramSocket.close();
    }
}
