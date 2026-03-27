package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.interfaces.Comands;

import java.net.Socket;

public class ExecuteScript implements Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        return new ResponsePacket("Было выполнено чтение из скрипта.",null);
    }

    @Override
    public String toString(){return "execute_script: Выполняет скрипт из указанного вами файла.";}
}
