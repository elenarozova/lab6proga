package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;

public class Help implements Comands {
    String text = "" ;
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        for (Comands i: Server.manPars.getCommands().values()){
            text = text + i.toString() + "\n";
        }
        return new ResponsePacket(text, null);
    }

    @Override
    public String toString(){
        return "help : Выводит значения всех команд";
    }

}
