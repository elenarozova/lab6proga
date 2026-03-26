package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.interfaces.Comands;

import java.net.Socket;
import server.Server;
public class ReplaceIfLowe implements Comands {

    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        Integer key = Integer.parseInt(args[0]);
        if (Server.colman.contains(key)) {
            if (labWork.compareTo(Server.colman.getLabWork().get(key))<0){
                Server.colman.getLabWork().replace(key,labWork);
                return new ResponsePacket("Объект был меньше, чем заданный вами, и был заменен.", null);
            }
        }
        return new ResponsePacket("Объект, заданный вами был больше.Коллекция не изменилась.", null);
    }

    @Override
    public String toString(){
        return "replace_if_lowe: Заменяет значение по ключу, если новое значение меньше старого.";
    }
}
