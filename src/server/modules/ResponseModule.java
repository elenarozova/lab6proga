package server.modules;

import data.ResponsePacket;
import server.Server;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ResponseModule {
    private Socket clientSocket;
    private ObjectOutputStream os;

    public ResponseModule(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.os = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    public void sendResponse(ResponsePacket packet) throws IOException {
        os.writeObject(packet);
        os.flush();
        Server.logger.info("Ответ отправлен: " + packet.getMessage());
    }
    public void close() {
        try {
            if (os != null) {
                os.close();
            }
        } catch (IOException e) {
            System.err.println("Ошибка при закрытии ResponseSender: " + e.getMessage());
        }
    }
}
