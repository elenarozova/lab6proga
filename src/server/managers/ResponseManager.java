package server.managers;

import data.CommandPacket;
import data.ResponsePacket;
import server.Server;

import java.io.*;
import java.net.Socket;

public class ResponseManager {

    public ResponsePacket get(Socket socket){
        try {
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream()) ;
            CommandPacket coomand = (CommandPacket) inputStream.readObject();
            ResponsePacket response = Server.manPars.parseCommand(coomand, socket);
            Server.logger.info(response.getMessage());
            return response;
        } catch (InvalidClassException | NotSerializableException exception) {
            Server.logger.error("Соединение не прошло 1");
        } catch (ClassNotFoundException exception) {
            Server.logger.error("Соединение не прошло 2");
        } catch (IOException exception) {
            Server.logger.error("Соединение не прошло 3");
        }
        return null;
    }

}
