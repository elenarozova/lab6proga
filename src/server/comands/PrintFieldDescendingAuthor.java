package server.comands;

import data.LabWork;
import data.Person;
import data.ResponsePacket;
import server.Server;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class PrintFieldDescendingAuthor implements server.interfaces.Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        TreeMap<Integer, LabWork> laba = Server.colman.getLabWork();
        if(laba.size()>0) {
            ArrayList<Person> uniqueAuthor = laba.values().stream()
                    .map(LabWork::getAuthor)
                    .distinct()
                    .collect(Collectors.toCollection(ArrayList::new));
            uniqueAuthor.sort((el1,el2) -> -el1.compareTo(el2));
            return new ResponsePacket("Уникальные значения author: ", uniqueAuthor);
        }
        return new ResponsePacket("Коллекция пуста", null);
    }

    @Override
    public String toString(){
        return "print_field_descending_author: Выводит всех авторов лабораторных работ и их данные в порядке убывания";
    }
}
