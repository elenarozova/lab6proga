package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.interfaces.Comands;
import server.Server;
import java.net.Socket;

public class Clear implements Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        Server.colman.clear();
        return new ResponsePacket("Коллекция очищена.", null);
    }

    @Override
    public String toString(){
        return "clear: Очищает словарь";
    }
}
