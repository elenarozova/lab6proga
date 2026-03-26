package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;
import java.util.Map;

public class Show implements Comands {

    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        Map<Integer,LabWork> labWorkMap = Server.colman.getLabWork();
        if (labWorkMap.size() == 0){
            return new ResponsePacket("В коллекции нет объектов", null);
        }
        return  new ResponsePacket("Объекты коллекции:", labWorkMap);
    }


    @Override
    public String toString(){
        return "show: Выводит все элементы коллекции";
    }

}
