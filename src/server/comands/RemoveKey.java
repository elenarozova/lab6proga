package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;

public class RemoveKey implements Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        Integer key = Integer.parseInt(args[0]);
        if (Server.colman.contains(key)){
            Server.colman.remove(key);
            return new ResponsePacket("Объект под ключом " + key + " был удален.", null);
        }
        return new ResponsePacket("Значения с ключом " + key + " нет в коллекции.", null);
    }

    @Override
    public String toString(){
        return "remove_key: Удалить элемент из коллекции по его ключу";
    }

}
