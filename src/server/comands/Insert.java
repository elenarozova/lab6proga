package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;

public class Insert implements Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        LabWork laba =  labWork;
        laba.setId(Server.genMan.generateId());
        Server.colman.insert(Integer.parseInt(args[0]),laba);
        return new ResponsePacket("Объект (Лабораторная работа) был добавлен в коллекцию.", labWork);
    }

    @Override
    public String toString(){return "insert: Добавить новый элемент с заданным ключом";}


}
