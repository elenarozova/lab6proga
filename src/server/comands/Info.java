package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;

public class Info implements Comands {
    String text = "";
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        text = "Тип коллекции: TreeMap \n"
                + "Количество элементов: " + Server.colman.size() + "\n"
                + "Время создания коллекции: " + Server.colman.getTime();
        return new ResponsePacket("Информация о коллекции", text);
    }
    @Override
    public String toString(){
        return "info: Выводит информацию о коллекции";
    }

}
