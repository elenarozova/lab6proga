package client.managers;

import client.Client;
import data.ResponsePacket;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.channels.SocketChannel;

public class CommandManager {
    private ObjectInputStream inputStream;

    public ResponsePacket get(SocketChannel socketChannel){
        try {
            inputStream = new ObjectInputStream(socketChannel.socket().getInputStream());
            ResponsePacket responsePacket = (ResponsePacket) inputStream.readObject();
            Client.inout.write(responsePacket.getMessage());
            return responsePacket;
        } catch (IOException | ClassNotFoundException e) {
            Client.inout.write("Не удалось прочитать ответ");
        }
        return new ResponsePacket(null, null);
    }
}
