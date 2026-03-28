package server.modules;

import data.CommandPacket;
import data.ResponsePacket;
import server.Server;
import server.managers.SendingManager;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class ConnectModule {
    private ServerSocket socket;
    private int port;
    private int soTimeout;

    public ConnectModule(int port, int soTimeout){
        this.port = port;
        this.soTimeout = soTimeout;
    }

    public void start()  {
        try {
            socket = new ServerSocket(port);
            socket.setSoTimeout(soTimeout);
            Server.logger.info("Сервер запущен на порту " + port);
        } catch (IOException e) {
            Server.logger.error("Не получилось открыть сервер");
        }
    }

    public void run() {
        try {
            start();
            while (true) {
                try (Socket clientSocket = connectToClient()) {
                    ResponsePacket response = Server.responseMan.get(clientSocket);
                    new SendingManager().send(clientSocket, response);
                } catch (IOException e) {
                    Server.logger.error("Проблемы с сервером 1");
                    break;
                }
            }
            stop();
        } catch (Exception e) {
            Server.logger.error("Проблемы с сервером 2");
        }
    }

    private Socket connectToClient()  {
        try{
            Server.logger.info("Ждём клиента");
            Socket clientSocket = socket.accept();
            Server.logger.info("Клиент подключен");
            return clientSocket;
        } catch (SocketTimeoutException exception) {
            Server.logger.warn("Время для подключения вышло");
        } catch (IOException exception) {
            Server.logger.warn("Клиент не подключён");
        }
        return null;
    }

    private void stop(){
        try{
            Server.logger.info("Сервер останавливается");
            if(socket == null) {
                socket.close();
                Server.logger.info("Сервер был остановлен");
            }
        } catch (IOException e) {
            Server.logger.warn("An error occurred when shutting down the server!");
        }
    }

}
