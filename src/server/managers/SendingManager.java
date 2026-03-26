package server.managers;

import data.ResponsePacket;
import server.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class SendingManager {
    public void send(Socket socket, ResponsePacket response) {
        try {
            ObjectOutputStream clientWriter = new ObjectOutputStream(socket.getOutputStream());
            clientWriter.writeObject(response);
            clientWriter.flush();
        } catch (IOException exception) {
            Server.logger.warn("Client is disconnected!");
        }
    }
}
