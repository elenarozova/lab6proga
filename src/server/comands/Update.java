package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;

public class Update implements Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        Integer key = Integer.parseInt(args[0]);
        if (Server.colman.contains(key)){
            Server.colman.getLabWork().replace(key,labWork);
            return new ResponsePacket("Значение коллекции, ключ которой - " + key + ", было заменено.", null);
        }
        return new ResponsePacket("Значения под ключом " + key + " не существует.",null);
    }

    @Override
    public String toString(){
        return "update: Обновить значение элемента коллекции, id которого равен заданному";
    }

}
