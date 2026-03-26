package client.managers;

import client.Client;
import data.CommandPacket;
import data.ResponsePacket;
import server.Server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class ConnectManager {

    private SocketChannel channel;
    private String host;
    private int port;

    public ConnectManager(String address, int port){
        this.host = address;
        this.port = port;
    }

    public void connect(){
        try {
            channel = SocketChannel.open(new InetSocketAddress(host,port));

        } catch (IOException | IllegalArgumentException e) {
            Client.inout.write("Сервер не подключен");
        }
    }

    public ResponsePacket sendCommand(CommandPacket command) {
        SendingManager sendingManager = new SendingManager();
        try {
            connect();
            sendingManager.send(command, channel);
            ResponsePacket responsePacket = Client.coMan.get(channel);
            sendingManager.stopOutput();
            close();
            return responsePacket;
        } catch (IOException e) {
            Client.inout.write("Не удалось закрыть соединение");
            return new ResponsePacket(null, null);
        }
    }


    public void close() throws IOException {
        channel.close();
    }

}
