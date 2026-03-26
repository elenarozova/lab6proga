package client.managers;

import client.Client;
import data.CommandPacket;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.channels.SocketChannel;

public class SendingManager {
    private ObjectOutputStream outputStream;

    public void send(CommandPacket request, SocketChannel socketChannel){
        try {
            outputStream = new ObjectOutputStream(socketChannel.socket().getOutputStream());
            outputStream.writeObject(request);
        }catch (IOException exception){
            Client.inout.write("Соединение с сервером не установлено");
        }
    }

    public void stopOutput() throws IOException{
        outputStream.close();
    }
}

