package server.comands;

import data.LabWork;
import data.ResponsePacket;
import server.Server;
import server.interfaces.Comands;

import java.net.Socket;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class FilterLessThanMinimalPoint implements Comands {
    @Override
    public ResponsePacket implementCommand(String[] args, LabWork labWork, Socket clientChannel) {
        Double minPoint = Double.parseDouble(args[0]);
        TreeMap<Integer,LabWork>  lab = Server.colman.getLabWork();
        TreeMap<Integer,LabWork> filter = lab.entrySet().stream()
                .filter(l -> l.getValue().getMinimalPoint()<minPoint)
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (v1,v2)->v1,
                        TreeMap::new
                ));
        return new ResponsePacket("Отфильтрованная коллекция:", filter);
    }
    @Override
    public String toString(){
        return "filter_less_than_minimal_point: Выводит все элементы, у которых значения минимального значения меньше заданного ";
    }
}
