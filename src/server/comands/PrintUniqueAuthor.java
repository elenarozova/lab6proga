package server.comands;

import data.LabWork;
import data.Person;
import data.ResponsePacket;
import server.Server;

import java.net.Socket;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PrintUniqueAuthor implements server.interfaces.Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        TreeMap<Integer, LabWork> laba = Server.colman.getLabWork();
        if(laba.size()>0) {
            List<Person> uniqueAuthor = laba.values().stream()
                    .map(LabWork::getAuthor)
                    .distinct()
                    .toList();
            return new ResponsePacket("Уникальные значения author: ", uniqueAuthor);
        }
        return new ResponsePacket("Коллекция пуста", null);
    }


    @Override
    public String toString(){
        return "print_unique_author: Выводит всех авторов лабораторных работ.";
    }
}
