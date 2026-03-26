package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class RemoveGreater implements Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        TreeMap<Integer,LabWork> labaW  = Server.colman.getLabWork();
        TreeMap<Integer, LabWork> filtLab = labaW.entrySet().stream()
                        .filter(entry ->entry.getValue().compareTo(labWork)<0)
                                .collect(Collectors.toMap(
                                        Map.Entry::getKey,
                                        Map.Entry::getValue,
                                        (v1, v2) -> v1,
                                        TreeMap::new
                                ));
        Server.colman.setLabWork(filtLab);
        return new ResponsePacket("Были удалены все элементы, меньшие заданного", null);
    }
    @Override
    public String toString(){return "remove_greater: Удаляет все элементы, которые больше заданного элемента";}

}
